package com.nifcompany.kingboard.json.post;

public class PostText{

	private String id;
	private String judul;
	private String text;

	public PostText() {
	}

	public PostText(String id, String judul, String text) {
		this.id = id;
		this.judul = judul;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}