package com.example.springvalidatordemo.service;

import cn.hutool.core.convert.Convert;
import com.example.springvalidatordemo.model.dto.User;
import com.example.springvalidatordemo.model.vo.NewUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * UserService.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Component
public class UserService {
    
    private final List<User> users = new CopyOnWriteArrayList<>();
    
    private final AtomicLong idSequence = new AtomicLong();
    
    public boolean create(final NewUser newUser) {
        final User user = Convert.convert(User.class, newUser);
        user.setId(idSequence.getAndIncrement());
        users.add(user);
        return true;
    }
    
    public List<User> all() {
        return new ArrayList<>(users);
    }
}
