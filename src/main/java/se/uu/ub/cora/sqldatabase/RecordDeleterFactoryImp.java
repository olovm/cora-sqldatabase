/*
 * Copyright 2020 Uppsala University Library
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

import se.uu.ub.cora.connection.SqlConnectionProvider;

public class RecordDeleterFactoryImp implements RecordDeleterFactory {

	private SqlConnectionProvider sqlConnectionProvider;

	private RecordDeleterFactoryImp(SqlConnectionProvider sqlConnectionProvider) {
		this.sqlConnectionProvider = sqlConnectionProvider;
	}

	public static RecordDeleterFactoryImp usingSqlConnectionProvider(
			SqlConnectionProvider sqlConnectionProvider) {
		return new RecordDeleterFactoryImp(sqlConnectionProvider);
	}

	@Override
	public RecordDeleter factor() {
		DataUpdater dataUpdater = DataUpdaterImp.usingSqlConnectionProvider(sqlConnectionProvider);
		return new RecordDeleterImp(dataUpdater);
	}

	@Override
	public SqlConnectionProvider getSqlConnectionProvider() {
		return sqlConnectionProvider;
	}

}
