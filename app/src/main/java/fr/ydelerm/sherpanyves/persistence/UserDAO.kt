package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ydelerm.sherpanyves.model.User

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Query("SELECT * FROM user")
    fun loadAllUsers(): LiveData<List<User>>
}