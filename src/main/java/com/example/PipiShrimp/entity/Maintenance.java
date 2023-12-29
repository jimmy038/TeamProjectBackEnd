package com.example.PipiShrimp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "maintenance")
public class Maintenance {

 @Id
 @Column(name = "record_id")
 private int recordId;

 @Column(name = "maintenance_cost")
 private int maintenanceCost;

 public Maintenance() {
  super();
  // TODO Auto-generated constructor stub
 }

 public Maintenance(int recordId, int maintenanceCost) {
  super();
  this.recordId = recordId;
  this.maintenanceCost = maintenanceCost;
 }

 public int getRecordId() {
  return recordId;
 }

 public void setRecordId(int recordId) {
  this.recordId = recordId;
 }

 public int getMaintenanceCost() {
  return maintenanceCost;
 }

 public void setMaintenanceCost(int maintenanceCost) {
  this.maintenanceCost = maintenanceCost;
 }

}