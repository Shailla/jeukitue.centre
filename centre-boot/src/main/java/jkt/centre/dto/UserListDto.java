package jkt.centre.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class UserListDto {
	
	@Getter @Setter
	private long total;
	
	@Getter @Setter
	private List<UserDto> users;
}
