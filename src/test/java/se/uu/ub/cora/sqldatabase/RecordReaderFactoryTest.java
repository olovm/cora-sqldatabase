/*
 * Copyright 2018, 2019 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.sqldatabase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.logger.LoggerProvider;
import se.uu.ub.cora.sqldatabase.log.LoggerFactorySpy;

public class RecordReaderFactoryTest {
	private SqlConnectionProviderSpy connectionProvider;
	private RecordReaderFactoryImp readerFactory;
	private LoggerFactorySpy loggerFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		loggerFactorySpy = new LoggerFactorySpy();
		LoggerProvider.setLoggerFactory(loggerFactorySpy);
		connectionProvider = new SqlConnectionProviderSpy();
		readerFactory = RecordReaderFactoryImp.usingSqlConnectionProvider(connectionProvider);
	}

	@Test
	public void testInit() throws Exception {
		assertEquals(readerFactory.getSqlConnectionProvider(), connectionProvider);
	}

	@Test
	public void testFactor() throws Exception {
		RecordReader recordReader = readerFactory.factor();
		assertTrue(recordReader instanceof RecordReaderImp);
	}

	@Test
	public void testDataReaderInRecordReader() throws Exception {
		RecordReaderImp recordReader = (RecordReaderImp) readerFactory.factor();
		DataReaderImp dataReader = (DataReaderImp) recordReader.getDataReader();
		assertSame(dataReader.getSqlConnectionProvider(), connectionProvider);
	}
}
