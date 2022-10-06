package com.mps.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Entity
@Table(name = "patient_tab")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pat_fn_col")
	private String firstName;
	@Column(name = "pat_ln_col")
	private String lastName;
	@Column(name = "pat_gen_col")
	private String gender;
	
	@Column(name = "pat_dob_col")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "pat_mob_col")
	private String mobile;
	@Column(name = "pat_ms_col")
	private String maritalStatus;
	@Column(name = "pat_paddr_col")
	private String presentAddress;
	@Column(name = "pat_caddr_col")
	private String communicationAddress;
	
	@ElementCollection
	@CollectionTable(name = "pat_med_hist_tab",
			joinColumns = @JoinColumn(name="pat_med_hist_id_fk_col")			
			)
	@Column(name="pat_med_hist_col")
	private Set<String> medHistory;
	
	@Column(name = "pat_other_col")
	private String ifOther;
	@Column(name = "pat_note_col")
	private String note;

}
