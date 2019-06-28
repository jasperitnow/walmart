package com.walmart.assignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class WalmartAssignmentApplication implements CommandLineRunner {

 	int N_PRODUCERS = 3;
	int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
	int poisonPill = Integer.MAX_VALUE;
	int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
	int mod = N_CONSUMERS % N_PRODUCERS;

	@Value("${issueIdStart}")
	private int issueIdStart;
	@Value("${issueIdEnd}")
	private int issueIdEnd;
	private int issueId;
	Map<Integer, Issue> issueMaster = new HashMap<>();
	Map<String, Technician> techMaster = new ConcurrentHashMap<>();
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	IssueProducer issueProducer;

	BlockingQueue<Issue> issuesQueueFromMultipleSources = new LinkedBlockingQueue<>();

	public static void main(String[] args) {
		SpringApplication.run(WalmartAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.setTestData();

		for (int i = 0; i < N_PRODUCERS; i++) {

			Thread t = new Thread(() -> {

				while (true) {

					issueId = ThreadLocalRandom.current().nextInt(issueIdStart, issueIdEnd);
					try {
						Thread.sleep(8000);
						issuesQueueFromMultipleSources.put(issueMaster.get(issueId));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			});
			t.setName("Producer " + i);
			t.start();
			System.out.println(t.getName() + " Started");
		}

		for (int j = 0; j < N_CONSUMERS; j++) {

			Thread q = new Thread(() -> {

				while (true) {

					try {

						System.out.println(issuesQueueFromMultipleSources);

						Thread.sleep(2000); // Just to monitor the output

						assignIssue(issuesQueueFromMultipleSources);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			});

			q.setName("Consumer " + j);
			q.start();
			System.out.println(q.getName() + " Started");

		}

	}

	private void assignIssue(BlockingQueue<Issue> issuesQueueFromMultipleSources2) throws InterruptedException {

		Issue issue = issuesQueueFromMultipleSources2.take();
		Technician techObj = null;
		boolean taskCompleted = false;

		if (techMaster.get(issue.getFirstPrefferedVendorType()) != null) {
			techObj = techMaster.remove(issue.getFirstPrefferedVendorType());
			if (techObj != null) {
				System.out.println("Issue : " + issue.getIssueName() + " is Assigned to :" + techObj.getTechName());
				sendEmail(issue, techObj);
				Thread.sleep(issue.getEstimatedCompletionTime());
				taskCompleted = true;
				System.out.println("Issue : " + issue.getIssueName() + " is Assigned to :" + techObj.getTechName()
						+ " has been completed.");
			}
		} else if (techMaster.get(issue.getSecondPrefferedVendorType()) != null) {
			techObj = techMaster.remove(issue.getSecondPrefferedVendorType());
			if (techObj != null) {
				System.out.println("Issue : " + issue.getIssueName() + " is Assigned to :" + techObj.getTechName());
				sendEmail(issue, techObj);
				Thread.sleep(issue.getEstimatedCompletionTime());
				taskCompleted = true;
				System.out.println("Issue : " + issue.getIssueName() + " is Assigned to :" + techObj.getTechName()
						+ " has been completed.");
			}
		} else {
			System.out.println("No available Technician , Please wait..!");
			issuesQueueFromMultipleSources2.add(issue);
		}

		if (taskCompleted)
			techMaster.put(techObj.getPrimaryTechType(), techObj);

	}

	private void setTestData() {

		this.setProducerTestData();
		this.setConsumerTestData();

	}

	private void setConsumerTestData() {

		techMaster.put("SPIDER_MAN", new Technician("Spider Man", "SPIDER_MAN", 5, Arrays.asList(101, 103), "su.man@aol.com"));
		techMaster.put("IRON_MAN", new Technician("Iron Man", "IRON_MAN", 4, Arrays.asList(102, 103), "suman.boddukuru@hsn.net"));
		techMaster.put("HULK", new Technician("Hulk", "HULK", 3, Arrays.asList(102, 103), "suman.tampa@gmail.com"));

	}

	private void setProducerTestData() {

		issueMaster.put(101, new Issue(101, "Building Window Washing", "SPIDER_MAN", "IRON_MAN", 1, 100, 5000, 5));
		issueMaster.put(102, new Issue(102, "Security Guard", "HULK", "IRON_MAN", 2, 200, 1000, 4));
		issueMaster.put(103, new Issue(103, "Delivery Service", "IRON_MAN", "SPIDER_MAN", 3, 300, 3000, 3));

	}
	
	void sendEmail(Issue is, Technician tec) {

		// Email notification or SMS notification using twilio
		/*
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(tec.getEmailId());

        msg.setSubject("New Issue Assignment");
        msg.setText("The issue : "+ is.getIssueName()+ " is Assigned to you"); 

        javaMailSender.send(msg);
		 */
		System.out.println("Sendng email to : "+ tec.getEmailId() + " for the Issue : " + is.getIssueName());
    }

}
