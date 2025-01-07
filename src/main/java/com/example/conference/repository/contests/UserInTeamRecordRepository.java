package com.example.conference.repository.contests;

import com.example.conference.contests.CriteriaMark;
import com.example.conference.contests.TeamRecord;
import com.example.conference.contests.UserInTeamRecord;
import com.example.conference.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface UserInTeamRecordRepository extends CrudRepository<UserInTeamRecord,Long> {

    @Query("SELECT ur FROM UserInTeamRecord ur join ur.teamRecord tr where tr.id = :teamRecordId")
    List<UserInTeamRecord> teamRecordsUsers(@Param("teamRecordId") Long id);

    @Query("SELECT u FROM User u where u.id IN :usersIds")
    List<User> usersByIds(@Param("usersIds") Collection<Long> usersId);

}
