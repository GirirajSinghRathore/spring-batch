/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.batch.sample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class TaskletJobFunctionalTests extends AbstractValidatingBatchLauncherTests {

	@Test
	public void testLaunchJob() throws Exception {
		setJobParameters(new JobParametersBuilder().addString("value", "foo").toJobParameters());
		super.testLaunchJob();
	}

	@Override
	protected void validatePostConditions() throws Exception {
		JobExecution jobExecution = getJobExecution();
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}

	public static class TestBean {
		private String value;

		public void setValue(String value) {
			this.value = value;
		}

		public void execute() {
			assertEquals("foo", value);
		}
	}

}
