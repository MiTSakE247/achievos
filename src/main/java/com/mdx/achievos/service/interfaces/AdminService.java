package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.request.LevelRequest;

public interface AdminService {

    String getAllLevels(Long userId, LevelRequest request);
}
