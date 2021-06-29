package com.syntra.tristanbrewee.miniCrm.model.idclasses;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MemberIdTest {

    private MemberId memberId1;
    private MemberId memberId2;
    private MemberId memberIdNull;
    private Integer personId1;
    private Integer personId2;
    private Integer communityId1;
    private Integer communityId2;

    @BeforeEach
    void setUp() {
        memberId1 = new MemberId();
        memberId2 = new MemberId();
        memberIdNull = null;
        personId1 = 1;
        personId2 = 2;
        memberId1.setPerson_id(personId1);
        memberId2.setPerson_id(personId1);
        communityId1 = 1;
        communityId2 = 2;
        memberId1.setCommunity_id(communityId1);
        memberId2.setCommunity_id(communityId1);
    }

    @AfterEach
    void tearDown() {
        memberId1 = null;
        memberId2 = null;
        personId1 = null;
        personId2 = null;
        communityId1 = null;
        communityId2 = null;
    }

    @Test
    void testEqualsSameObject() {
        assertTrue(memberId1.equals(memberId1));
    }

    @Test
    void testEqualsObjectIsNull(){
        assertFalse(memberId1.equals(memberIdNull));
    }

    @Test
    void testEqualsObjectIsDifferentClass(){
        assertFalse(memberId1.equals(personId1));
    }

    @Test
    void testEqualsDifferentPersonId(){
        memberId2.setPerson_id(personId2);
        assertFalse(memberId1.equals(memberId2));
    }

    @Test
    void testEqualsDifferentCommunityId(){
        memberId2.setCommunity_id(communityId2);
        assertFalse(memberId1.equals(memberId2));
    }

    @Test
    void testEqualsSameValues(){
        assertTrue(memberId1.equals(memberId2));
    }

    @Test
    void testHashCode() {
        Object[] objects = {communityId1, personId1};
        assertEquals(memberId1.hashCode(), Arrays.hashCode(objects));
    }

    @Test
    void getCommunity_id() {
        assertEquals(memberId1.getCommunity_id(), communityId1);
    }

    @Test
    void setCommunity_id() {
        memberId1.setCommunity_id(communityId2);
        assertEquals(memberId1.getCommunity_id(), communityId2);
        assertNotEquals(memberId1.getCommunity_id(), communityId1);
    }

    @Test
    void getPerson_id() {
        assertEquals(memberId1.getPerson_id(), personId1);
    }

    @Test
    void setPerson_id() {
        memberId1.setPerson_id(personId2);
        assertEquals(memberId1.getPerson_id(), personId2);
        assertNotEquals(memberId1.getPerson_id(), personId1);
    }
}