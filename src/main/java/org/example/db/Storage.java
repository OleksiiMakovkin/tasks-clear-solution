package org.example.db;

import org.example.model.User;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    public static final Map<Long, User> usersStorage = new HashMap<>();
}
