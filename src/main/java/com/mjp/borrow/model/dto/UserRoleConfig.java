package com.mjp.borrow.model.dto;

import com.mjp.borrow.model.Role;
import com.mjp.borrow.model.UserInfo;
import lombok.Data;

import java.util.List;

/**
 * <p>Description：</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 10:47
 */
@Data
public class UserRoleConfig {
    private Role role;

    private List<UserInfo> userInfoList;

}
