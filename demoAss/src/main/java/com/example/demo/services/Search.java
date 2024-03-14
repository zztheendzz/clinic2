package com.example.demo.services;

public class Search {
//	hàm kiểm tra từ ng dùng viết
	public boolean Search(String keyWord, String refeWord) {
		// keyWord : từ tìm kiếm ng dùng gõ
		// refeWord: từ so sánh
		int count = 0;
		if(refeWord == null || refeWord=="") {return false;}
		for (int i = 0; i < refeWord.length(); i++) { 
			for (int j = 0; j < keyWord.length(); j++) {
				if (keyWord.charAt(j) == refeWord.charAt(i)) {
					count++;
				}
			}
		}
		if (count *2  >=  refeWord.length()) {
			return true;
		}
		return false;
	}
	public Double DSearch(String keyWord, String refeWord) {
		// keyWord : từ tìm kiếm ng dùng gõ
		// refeWord: từ so sánh
		int count = 0;
		int countRefeW = refeWord.length();
		Double percent = 0.0;
		for (int i = 0; i < refeWord.length(); i++) { 
			for (int j = 0; j < keyWord.length(); j++) {
				if (keyWord.charAt(j) == refeWord.charAt(i)) {
					count++;
				}
			}
		}
		percent = Math.floor(count/countRefeW * 10) / 10;
		return percent;
	}

}
