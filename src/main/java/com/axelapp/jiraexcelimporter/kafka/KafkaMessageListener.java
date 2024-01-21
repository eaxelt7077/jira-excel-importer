package com.axelapp.jiraexcelimporter.kafka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.axelapp.jiraexcelimporter.model.custom.TaskSummaryQueueBean;
import com.axelapp.jiraexcelimporter.model.database.EmployeeTaskSummary;
import com.axelapp.jiraexcelimporter.service.base.BaseService;
import com.google.gson.Gson;

@Component
public class KafkaMessageListener extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageListener.class);

    private static final String KAKFA_TOPIC = "axelapp";

    @KafkaListener(topics = KAKFA_TOPIC, groupId = "1", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        LOG.info("Consuming: {}", message);
        try {
            insertTaskSummaryFromMessage(message);
        } catch (Exception e) {
            LOG.error("Failed to insert employee task summary", e);
        }
    }

    private void insertTaskSummaryFromMessage(String message) throws ParseException {
        TaskSummaryQueueBean bean = new Gson().fromJson(message, TaskSummaryQueueBean.class);
        Long idEmployee = daoFactory.getEmployeeDao().getIdEmployeeByEmail(bean.getPicEmail());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date finishTime = sdf.parse(bean.getFinishTime());

        EmployeeTaskSummary summary = new EmployeeTaskSummary();
        summary.setTaskType(bean.getTaskType());
        summary.setTaskKey(bean.getTaskKey());
        summary.setTaskDescription(bean.getTaskDesc());
        summary.setTaskStatus(bean.getTaskStatus());
        summary.setFinishTime(finishTime);
        summary.setTimeSpentSeconds(bean.getTimeSpentInSeconds());
        summary.setTimeEstimationSeconds(bean.getEstimateInSeconds());
        summary.setTimesReturned(bean.getTimesReturned().intValue());
        summary.setIdEmployee(idEmployee);
        summary.setCreatedBy("KAFKA_LISTENER");
        summary.setCreatedAt(new Date());
        daoFactory.getEmployeeTaskSummaryDao().insertEmployeeTaskSummary(summary);
    }

}
