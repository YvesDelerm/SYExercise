package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.ydelerm.sherpanyves.model.User
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Query("SELECT * FROM user")
    fun loadAllUsers(): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM user where userId= :givenUserId")
    fun loadUserWithAlbumsAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?>
}