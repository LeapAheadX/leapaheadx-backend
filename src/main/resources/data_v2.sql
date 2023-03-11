-- Insertion of Users
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79eb9b5e-bd58-11ed-afa1-0242ac120002','admin@gmail.com', MD5('admin'), 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79eb9e38-bd58-11ed-afa1-0242ac120002','admin2@gmail.com', MD5('admin2'), 'admin2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79eb9fd2-bd58-11ed-afa1-0242ac120002','approver@gmail.com', MD5('approver'), 'approver', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79eba1a8-bd58-11ed-afa1-0242ac120002','approver2@gmail.com', MD5('approver2'), 'approver2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebaad6-bd58-11ed-afa1-0242ac120002','vendor@gmail.com', MD5('vendor'), 'vendor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebad60-bd58-11ed-afa1-0242ac120002','vendor2@gmail.com', MD5('vendor2'), 'vendor2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- Assignment of Users to Admins
INSERT INTO admin (admin_uuid, u_id, department, country, date_created, last_updated) 
VALUES ('79ebaf36-bd58-11ed-afa1-0242ac120002', '79eb9b5e-bd58-11ed-afa1-0242ac120002' , 'OPS', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO admin (admin_uuid, u_id, department, country, date_created, last_updated) 
VALUES ('79ebb09e-bd58-11ed-afa1-0242ac120002', '79eb9e38-bd58-11ed-afa1-0242ac120002' , 'Legal', 'Malaysia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Assignment of Users to Approver

INSERT INTO approver (approver_uuid, u_id, approval_tier, date_created, last_updated) 
VALUES ('79ebb238-bd58-11ed-afa1-0242ac120002', '79eb9fd2-bd58-11ed-afa1-0242ac120002', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO approver (approver_uuid, u_id, approval_tier, date_created, last_updated) 
VALUES ('79ebb3aa-bd58-11ed-afa1-0242ac120002', '79eba1a8-bd58-11ed-afa1-0242ac120002', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Assignment of Users to Vendor
INSERT INTO vendor (vendor_uuid, u_id, company, country, date_created, last_updated) 
VALUES ('79ebb4e0-bd58-11ed-afa1-0242ac120002', '79ebaad6-bd58-11ed-afa1-0242ac120002', 'SMU', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO vendor (vendor_uuid, u_id, company, country, date_created, last_updated) 
VALUES ('79ebb7ec-bd58-11ed-afa1-0242ac120002', '79ebad60-bd58-11ed-afa1-0242ac120002', 'NTU', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Form_workflows
INSERT INTO form_workflow (form_uuid, name, description, created_by, date_created, last_updated) 
VALUES ('79ebc39a-bd58-11ed-afa1-0242ac120002', 'TestOne','This is a test form to mock the first one','79ebaf36-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO form_workflow (form_uuid, name, description, created_by, date_created, last_updated) 
VALUES ('79ebc5a2-bd58-11ed-afa1-0242ac120002', 'TestTwo','This is a test form to mock the second one','79ebaf36-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO form_workflow (form_uuid, name, description, created_by, date_created, last_updated) 
VALUES ('79ebcc0a-bd58-11ed-afa1-0242ac120002', 'TestThree','This is a test form to mock the third one','79ebaf36-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert subformCanvas
INSERT INTO subform_canvas (canvas_uuid, name, description, created_by, date_created, last_updated)
VALUES ('79ebce12-bd58-11ed-afa1-0242ac120002','SubCanvasOne','This is a test for SubCanvas to mock the first one','79ebaf36-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO subform_canvas (canvas_uuid, name, description, created_by, date_created, last_updated)
VALUES ('79ebcfc0-bd58-11ed-afa1-0242ac120002','SubCanvasTwo','This is a test for SubCanvasm to mock the second one','79ebaf36-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO subform_canvas (canvas_uuid, name, description, created_by, date_created, last_updated)
VALUES ('79ebd182-bd58-11ed-afa1-0242ac120002','SubCanvasThree','This is a test SubCanvas to mock the third one','79ebaf36-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

-- Insertion of Form Steps
INSERT INTO form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
VALUES ('79ebd34e-bd58-11ed-afa1-0242ac120002','79ebc39a-bd58-11ed-afa1-0242ac120002','vendor','1','Fill Up',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
VALUES ('79ebd8d0-bd58-11ed-afa1-0242ac120002','79ebc39a-bd58-11ed-afa1-0242ac120002','admin','2','Check and Fill Up',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
VALUES ('79ebdac4-bd58-11ed-afa1-0242ac120002','79ebc39a-bd58-11ed-afa1-0242ac120002','approver','3','Check and Approve',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
values ('79ebdc72-bd58-11ed-afa1-0242ac120002','79ebc5a2-bd58-11ed-afa1-0242ac120002','vendor','1','Fill Up',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
values ('79ebdeb6-bd58-11ed-afa1-0242ac120002','79ebc5a2-bd58-11ed-afa1-0242ac120002','admin','2','Check and Fill Up',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
values ('79ebe082-bd58-11ed-afa1-0242ac120002','79ebc5a2-bd58-11ed-afa1-0242ac120002','approver','3','Check and Approve',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
values ('79ebe24e-bd58-11ed-afa1-0242ac120002','79ebcc0a-bd58-11ed-afa1-0242ac120002','vendor','1','Fill Up',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
values ('79ebe488-bd58-11ed-afa1-0242ac120002','79ebcc0a-bd58-11ed-afa1-0242ac120002','admin','2','Check and Fill Up',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into form_step(step_uuid, parent_form, assignee_type, order_no, action, date_created, last_updated)
values ('79ebe92e-bd58-11ed-afa1-0242ac120002','79ebcc0a-bd58-11ed-afa1-0242ac120002','approver','3','Check and Approve',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of associated_subform
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebd34e-bd58-11ed-afa1-0242ac120002','79ebce12-bd58-11ed-afa1-0242ac120002', '1',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebd34e-bd58-11ed-afa1-0242ac120002','79ebcfc0-bd58-11ed-afa1-0242ac120002', '2',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebd8d0-bd58-11ed-afa1-0242ac120002','79ebd182-bd58-11ed-afa1-0242ac120002', '3',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebdac4-bd58-11ed-afa1-0242ac120002','79ebce12-bd58-11ed-afa1-0242ac120002', '1',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebdac4-bd58-11ed-afa1-0242ac120002','79ebcfc0-bd58-11ed-afa1-0242ac120002', '2',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebdac4-bd58-11ed-afa1-0242ac120002','79ebd182-bd58-11ed-afa1-0242ac120002', '3',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebdc72-bd58-11ed-afa1-0242ac120002','79ebce12-bd58-11ed-afa1-0242ac120002', '3',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebdc72-bd58-11ed-afa1-0242ac120002','79ebcfc0-bd58-11ed-afa1-0242ac120002', '2',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebdeb6-bd58-11ed-afa1-0242ac120002','79ebd182-bd58-11ed-afa1-0242ac120002', '1',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebe082-bd58-11ed-afa1-0242ac120002','79ebce12-bd58-11ed-afa1-0242ac120002', '2',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebe082-bd58-11ed-afa1-0242ac120002','79ebcfc0-bd58-11ed-afa1-0242ac120002', '3',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO associated_subform (step_uuid, canvas_uuid, position, date_created, last_updated)
VALUES ('79ebe082-bd58-11ed-afa1-0242ac120002','79ebd182-bd58-11ed-afa1-0242ac120002', '1',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO input_component(component_uuid,parent_canvas,question,type,is_required,order_no, date_created, last_updated)
VALUES ('79ebf1ee-bd58-11ed-afa1-0242ac120002', '79ebce12-bd58-11ed-afa1-0242ac120002', 'Name', 'text', TRUE, '1',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO input_component(component_uuid,parent_canvas,question,type,is_required,order_no, date_created, last_updated)
VALUES ('79ebf338-bd58-11ed-afa1-0242ac120002', '79ebce12-bd58-11ed-afa1-0242ac120002', 'Age', 'integer', TRUE, '2',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO input_component(component_uuid,parent_canvas,question,type,is_required,order_no, date_created, last_updated)
VALUES ('79ebf45a-bd58-11ed-afa1-0242ac120002', '79ebcfc0-bd58-11ed-afa1-0242ac120002', 'Skill Level', 'dropdown', TRUE, '3',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO input_component(component_uuid,parent_canvas,question,type,is_required,order_no, date_created, last_updated)
VALUES ('79ebf608-bd58-11ed-afa1-0242ac120002', '79ebd182-bd58-11ed-afa1-0242ac120002', 'Gender', 'checkbox', TRUE, '4',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebfb9e-bd58-11ed-afa1-0242ac120002', 'Male', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebfd24-bd58-11ed-afa1-0242ac120002', 'Female', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebfe78-bd58-11ed-afa1-0242ac120002', 'Low', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ec0044-bd58-11ed-afa1-0242ac120002', 'Medium', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ec01fc-bd58-11ed-afa1-0242ac120002', 'High', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebf608-bd58-11ed-afa1-0242ac120002', '79ebfb9e-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebf608-bd58-11ed-afa1-0242ac120002', '79ebfd24-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebf45a-bd58-11ed-afa1-0242ac120002', '79ebfe78-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebf45a-bd58-11ed-afa1-0242ac120002', '79ec0044-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebf45a-bd58-11ed-afa1-0242ac120002', '79ec01fc-bd58-11ed-afa1-0242ac120002');

INSERT INTO application (application_uuid, created_for, form_uuid, status, comment, current_step_no, date_created, last_updated)
VALUES ('79ec03aa-bd58-11ed-afa1-0242ac120002','79ebb4e0-bd58-11ed-afa1-0242ac120002','79ebc39a-bd58-11ed-afa1-0242ac120002','InProgress',NULL ,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO application (application_uuid, created_for, form_uuid, status, comment, current_step_no, date_created, last_updated)
VALUES ('79ec053a-bd58-11ed-afa1-0242ac120002','79ebb4e0-bd58-11ed-afa1-0242ac120002','79ebc5a2-bd58-11ed-afa1-0242ac120002','InProgress',NULL ,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO application (application_uuid, created_for, form_uuid, status, comment, current_step_no, date_created, last_updated)
VALUES ('79ec0da0-bd58-11ed-afa1-0242ac120002','79ebb4e0-bd58-11ed-afa1-0242ac120002','79ebcc0a-bd58-11ed-afa1-0242ac120002','InProgress',NULL ,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO application (application_uuid, created_for, form_uuid, status, comment, current_step_no, date_created, last_updated)
VALUES ('79ec0f4e-bd58-11ed-afa1-0242ac120002','79ebb7ec-bd58-11ed-afa1-0242ac120002','79ebc39a-bd58-11ed-afa1-0242ac120002','NotStarted',NULL ,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO application (application_uuid, created_for, form_uuid, status, comment, current_step_no, date_created, last_updated)
VALUES ('79ec10a2-bd58-11ed-afa1-0242ac120002','79ebb7ec-bd58-11ed-afa1-0242ac120002','79ebc5a2-bd58-11ed-afa1-0242ac120002','NotStarted',NULL ,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO application (application_uuid, created_for, form_uuid, status, comment, current_step_no, date_created, last_updated)
VALUES ('79ec1232-bd58-11ed-afa1-0242ac120002','79ebb7ec-bd58-11ed-afa1-0242ac120002','79ebcc0a-bd58-11ed-afa1-0242ac120002','NotStarted',NULL ,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO application_response_value (application_uuid, canvas_uuid,component_uuid,value, date_created, last_updated)
VALUES('79ec03aa-bd58-11ed-afa1-0242ac120002','79ebce12-bd58-11ed-afa1-0242ac120002', '79ebf1ee-bd58-11ed-afa1-0242ac120002', 'Jeff', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO application_response_value (application_uuid, canvas_uuid,component_uuid,value, date_created, last_updated)
VALUES('79ec053a-bd58-11ed-afa1-0242ac120002','79ebce12-bd58-11ed-afa1-0242ac120002', '79ebf338-bd58-11ed-afa1-0242ac120002', '21', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO application_response_value (application_uuid, canvas_uuid,component_uuid,value, date_created, last_updated)
VALUES('79ec0da0-bd58-11ed-afa1-0242ac120002','79ebcfc0-bd58-11ed-afa1-0242ac120002', '79ebf45a-bd58-11ed-afa1-0242ac120002', 'High', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO application_response_value (application_uuid, canvas_uuid,component_uuid,value, date_created, last_updated)
VALUES('79ec0f4e-bd58-11ed-afa1-0242ac120002','79ebd182-bd58-11ed-afa1-0242ac120002', '79ebf608-bd58-11ed-afa1-0242ac120002', 'Male', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
