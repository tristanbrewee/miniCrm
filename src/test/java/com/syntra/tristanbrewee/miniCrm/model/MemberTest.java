package com.syntra.tristanbrewee.miniCrm.model;

import com.syntra.tristanbrewee.miniCrm.model.idclasses.MemberId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Member member;
    private MemberId memberId1;
    private MemberId memberId2;
    private LocalDate since1;
    private LocalDate since2;
    private LocalDate until1;
    private LocalDate until2;

    @BeforeEach
    void setUp() {
        member = new Member();
        memberId1 = new MemberId(1, 1);
        memberId2 = new MemberId(2, 2);
        member.setMemberId(memberId1);
        since1 = LocalDate.now();
        since2 = LocalDate.now().minusDays(1);
        member.setSince(since1);
        until1 = LocalDate.now();
        until2 = LocalDate.now().minusDays(1);
        member.setUntil(until1);
    }

    @AfterEach
    void tearDown() {
        member = null;
        memberId1 = null;
        memberId2 = null;
        since1 = null;
        since2 = null;
        until1 = null;
        until2 = null;
    }

    @Test
    void getMemberId() {
        assertEquals(member.getMemberId(), memberId1);
    }

    @Test
    void setMemberId() {
        member.setMemberId(memberId2);
        assertEquals(member.getMemberId(), memberId2);
        assertNotEquals(member.getMemberId(), memberId1);
    }

    @Test
    void getSince() {
        assertEquals(member.getSince(), since1);
    }

    @Test
    void setSince() {
        member.setSince(since2);
        assertEquals(member.getSince(), since2);
        assertNotEquals(member.getSince(), since1);
    }

    @Test
    void getUntil() {
        assertEquals(member.getUntil(), until1);
    }

    @Test
    void setUntil() {
        member.setUntil(until2);
        assertEquals(member.getUntil(), until2);
        assertNotEquals(member.getUntil(), until1);
    }
}