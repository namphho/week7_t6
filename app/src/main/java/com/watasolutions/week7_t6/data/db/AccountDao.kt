package com.watasolutions.week7_t6.data.db

import androidx.room.*

/**
 * Created by nampham on 5/17/21.
 */
@Dao
interface AccountDao {
    @Query("SELECT * FROM account ORDER BY username ASC")
    suspend fun getAccounts(): List<Account>

    @Query("SELECT * FROM account WHERE username = :user AND " +
            "password = :pass")
    suspend fun searchAccount(user: String, pass: String): Account?

    @Query("SELECT * FROM account WHERE username = :user")
    suspend fun checkUsernameExisting(user: String): Account?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Query("DELETE FROM account")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(account: Account)

    @Update
    suspend fun update(account: Account)
}