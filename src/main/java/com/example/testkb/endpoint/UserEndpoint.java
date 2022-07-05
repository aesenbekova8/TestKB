package com.example.testkb.endpoint;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.response.UserView;

public interface UserEndpoint {
    UserView addCashier(UserCreateRequest request, @CurrentUser UserPrincipal currentUser);
    UserView updatePassword(PasswordUpdateRequest request, @CurrentUser UserPrincipal currentUser);
}
