package com.mps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="doctor_tab")
@Data
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_id_col")
	private Long id;
	
	@Column(name = "doc_fn_col")
	private String firstName;
	@Column(name = "doc_ln_col")
	private String lastName;
	@Column(name = "doc_mail_col")
	private String email;
	@Column(name = "doc_addr_col")
	private String address;
	@Column(name = "doc_mob_col")
	private String mobile;
	@Column(name = "doc_gen_col")
	private String gender;
	@Column(name = "doc_note_col")
	private String note;
}
