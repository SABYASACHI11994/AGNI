package com.agni.demo.service;

import org.springframework.core.io.Resource;

public interface FileStorageService
{



	Resource loadFileAsResource(String fileName) throws Exception;
}
