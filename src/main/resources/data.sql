

-- Insertion of Admin 1
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebf338-bd58-11ed-afa1-0242ac120002', 'admin@gmail.com', MD5('admin'), 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO admin (admin_uuid, u_id, department, country, date_created, last_updated) 
VALUES ('79eb9b5e-bd58-11ed-afa1-0242ac120002', '79ebf338-bd58-11ed-afa1-0242ac120002' , 'OPS', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Admin 2
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebf45a-bd58-11ed-afa1-0242ac120002', 'admin2@gmail.com', MD5('admin2'), 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO admin (admin_uuid, u_id, department, country, date_created, last_updated) 
VALUES ('79eb9e38-bd58-11ed-afa1-0242ac120002', '79ebf45a-bd58-11ed-afa1-0242ac120002', 'Legal', 'Malaysia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Approver 1
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebf608-bd58-11ed-afa1-0242ac120002','approver@gmail.com', MD5('approver'), 'approver', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO approver (approver_uuid, u_id, approval_tier, date_created, last_updated) 
VALUES ('79eb9fd2-bd58-11ed-afa1-0242ac120002', '79ebf608-bd58-11ed-afa1-0242ac120002', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Approver 2
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebfb9e-bd58-11ed-afa1-0242ac120002','approver2@gmail.com', MD5('approver2'), 'approver', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO approver (approver_uuid, u_id, approval_tier, date_created, last_updated) 
VALUES ('79eba1a8-bd58-11ed-afa1-0242ac120002', '79ebfb9e-bd58-11ed-afa1-0242ac120002', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Vendor 1
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebfd24-bd58-11ed-afa1-0242ac120002','vendor@gmail.com', MD5('vendor'), 'vendor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vendor (vendor_uuid, u_id, company, country, date_created, last_updated) 
VALUES ('79ebaad6-bd58-11ed-afa1-0242ac120002', '79ebfd24-bd58-11ed-afa1-0242ac120002', 'SMU', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Vendor 2
INSERT INTO user (u_id, email, password, role, date_created, last_updated) 
VALUES ('79ebfe78-bd58-11ed-afa1-0242ac120002','vendor2@gmail.com', MD5('vendor2'), 'vendor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vendor (vendor_uuid, u_id, company, country, date_created, last_updated) 
VALUES ('79ebad60-bd58-11ed-afa1-0242ac120002', '79ebfe78-bd58-11ed-afa1-0242ac120002', 'NTU', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Form 1
INSERT INTO form (form_uuid, created_by, name, description, date_created, last_updated) 
VALUES ('79ebaf36-bd58-11ed-afa1-0242ac120002', '79eb9b5e-bd58-11ed-afa1-0242ac120002', 'TestOne','This is a test form', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Form 2
INSERT INTO form (form_uuid, created_by, name, description, date_created, last_updated) 
VALUES ('79ebb09e-bd58-11ed-afa1-0242ac120002', '79eb9b5e-bd58-11ed-afa1-0242ac120002', 'TestTwo','This is a test form', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Form 3
INSERT INTO form (form_uuid, created_by, name, description, date_created, last_updated) 
VALUES ('79ebb238-bd58-11ed-afa1-0242ac120002', '79eb9b5e-bd58-11ed-afa1-0242ac120002', 'TestThree','This is a test form', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Workflow 1
INSERT INTO workflow (workflow_uuid, name, description, created_by, date_created, last_updated) 
VALUES ('79ebb3aa-bd58-11ed-afa1-0242ac120002', 'TestOne', 'This is the first Test Workflow','79eb9b5e-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Workflow 2
INSERT INTO workflow (workflow_uuid, name, description, created_by, date_created, last_updated) 
VALUES ('79ebb4e0-bd58-11ed-afa1-0242ac120002', 'TestTwo', 'This is the second Test Workflow','79eb9e38-bd58-11ed-afa1-0242ac120002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Assignment 1
INSERT INTO  vendor_workflow_assignment(workflow_uuid, vendor_uuid) 
VALUES ('79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebaad6-bd58-11ed-afa1-0242ac120002');

-- Insertion of Assignment 2
INSERT INTO  vendor_workflow_assignment(workflow_uuid, vendor_uuid) 
VALUES ('79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebad60-bd58-11ed-afa1-0242ac120002');

-- Insertion of Step 1-9
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebb7ec-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', '79ebf338-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '1', 'submit', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebc39a-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebb09e-bd58-11ed-afa1-0242ac120002', '79ebf338-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '1', 'submit', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebc5a2-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebb238-bd58-11ed-afa1-0242ac120002', '79ebf338-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '1', 'submit', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebcc0a-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', '79ebf608-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '2', 'check', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebce12-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebb09e-bd58-11ed-afa1-0242ac120002', '79ebf608-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '2', 'check', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebcfc0-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebb238-bd58-11ed-afa1-0242ac120002', '79ebf608-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '2', 'check', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebd182-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', '79ebfd24-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '3', 'approve', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebd34e-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebb09e-bd58-11ed-afa1-0242ac120002', '79ebfd24-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '3', 'approve', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('79ebd8d0-bd58-11ed-afa1-0242ac120002', '79ebb3aa-bd58-11ed-afa1-0242ac120002', '79ebb238-bd58-11ed-afa1-0242ac120002', '79ebfd24-bd58-11ed-afa1-0242ac120002', 'aStep', 'lorem lorem', '3', 'approve', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated) 
VALUES ('79ebdac4-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', 'Name', 'text', TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated) 
VALUES ('79ebdc72-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', 'Age', 'integer', TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated)
VALUES ('79ebdeb6-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', 'Skill Level', 'dropdown', TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated)
VALUES ('79ebe082-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', 'Gender', 'checkbox', TRUE, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebe24e-bd58-11ed-afa1-0242ac120002', 'Male', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebe488-bd58-11ed-afa1-0242ac120002', 'Female', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebe92e-bd58-11ed-afa1-0242ac120002', 'Low', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebecee-bd58-11ed-afa1-0242ac120002', 'Medium', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('79ebeef6-bd58-11ed-afa1-0242ac120002', 'High', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebdeb6-bd58-11ed-afa1-0242ac120002', '79ebe92e-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebdeb6-bd58-11ed-afa1-0242ac120002', '79ebecee-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebdeb6-bd58-11ed-afa1-0242ac120002', '79ebeef6-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebe082-bd58-11ed-afa1-0242ac120002', '79ebe24e-bd58-11ed-afa1-0242ac120002');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('79ebe082-bd58-11ed-afa1-0242ac120002', '79ebe488-bd58-11ed-afa1-0242ac120002');

INSERT INTO  application(application_uuid, form_uuid, submitted_by, status, date_created, last_updated) 
VALUES ('79ebf090-bd58-11ed-afa1-0242ac120002', '79ebaf36-bd58-11ed-afa1-0242ac120002', '1', 'inProgress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application(application_uuid, form_uuid, submitted_by, status, date_created, last_updated) 
VALUES ('79ebf1ee-bd58-11ed-afa1-0242ac120002', '79ebb09e-bd58-11ed-afa1-0242ac120002', '1', 'inProgress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('79ebf090-bd58-11ed-afa1-0242ac120002', '79ebdac4-bd58-11ed-afa1-0242ac120002', 'Jeff', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('79ebf090-bd58-11ed-afa1-0242ac120002', '79ebdc72-bd58-11ed-afa1-0242ac120002', '21', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('79ebf090-bd58-11ed-afa1-0242ac120002', '79ebdeb6-bd58-11ed-afa1-0242ac120002', '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('79ebf090-bd58-11ed-afa1-0242ac120002', '79ebe082-bd58-11ed-afa1-0242ac120002', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
