package com.mps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="specialization_tab")
@Data
public class Specialization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spec_id_col")
	private Long id;
	
	//Database level validation (@NotNull is backend level validation)
	@Column(name="spec_code_col", length = 10, nullable = false, unique = true)
	private String specCode;
	
	@Column(name="spec_name_col", length = 60, nullable = false, unique = true)
	private String specName;
	
	@Column(name="spec_note_col", length = 250, nullable = false, unique = true)
	private String specNote;
	
}
