package com.example.demo.service;

import java.util.Map;

public interface IUsuarioService {
	
	Map<String,String> onChangePassword(String email,String password,String updatePassword);

}
