package com.justcloud.model.test.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.solr.analysis.PhoneticFilterFactory;
import org.apache.solr.analysis.StandardFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import com.justcloud.model.test.domain.TestPerson;

@Entity
@Table(name = "TEST_PERSON")
@Indexed
@AnalyzerDef(name = "phonetic", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = StandardFilterFactory.class),
		@TokenFilterDef(factory = PhoneticFilterFactory.class, params = {
				@Parameter(name = "encoder", value = "DoubleMetaphone"),
				@Parameter(name = "inject", value = "false") }) })
public class TestPersonJpa implements TestPerson {

	private static final long serialVersionUID = -6377447941122747890L;

	private Long id;
	private String name;
	private String nickname;
	private String email;
	private Integer version;

	@Id
	@DocumentId
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	@Fields({
			@Field(analyzer = @Analyzer(impl = SpanishAnalyzer.class)),
			@Field(name = "name_phonetic", analyzer = @Analyzer(definition = "phonetic")) })
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Field
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Field
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
