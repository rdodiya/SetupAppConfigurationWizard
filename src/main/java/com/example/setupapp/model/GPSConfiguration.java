package com.example.setupapp.model;

public class GPSConfiguration {
	
	BasicConfiguration BasicConfiguration;
	DatabaseConfiguration DatabaseConfiguration;
	LDAPConfigurtion LDAPConfigurtion;
	SMTPConfiguration SMTPConfiguration;
	
	public BasicConfiguration getBasicConfiguration() {
		return BasicConfiguration;
	}

	public void setBasicConfiguration(BasicConfiguration basicConfiguration) {
		BasicConfiguration = basicConfiguration;
	}

	public DatabaseConfiguration getDatabaseConfiguration() {
		return DatabaseConfiguration;
	}

	public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
		DatabaseConfiguration = databaseConfiguration;
	}

	public LDAPConfigurtion getLDAPConfigurtion() {
		return LDAPConfigurtion;
	}

	public void setLDAPConfigurtion(LDAPConfigurtion lDAPConfigurtion) {
		LDAPConfigurtion = lDAPConfigurtion;
	}

	public SMTPConfiguration getSMTPConfiguration() {
		return SMTPConfiguration;
	}

	public void setSMTPConfiguration(SMTPConfiguration sMTPConfiguration) {
		SMTPConfiguration = sMTPConfiguration;
	}

	@Override
	public String toString() {
		return "GPSConfiguration [BasicConfiguration=" + BasicConfiguration + ", DatabaseConfiguration="
				+ DatabaseConfiguration + ", LDAPConfigurtion=" + LDAPConfigurtion + ", SMTPConfiguration="
				+ SMTPConfiguration + "]";
	}
	
	
}
