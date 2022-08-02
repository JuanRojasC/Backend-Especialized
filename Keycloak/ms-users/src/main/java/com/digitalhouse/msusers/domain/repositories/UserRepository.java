package com.digitalhouse.msusers.domain.repositories;

import com.digitalhouse.msusers.domain.models.User;

public interface UserRepository {
    User findUser(String id);
}
