-- Insertion of Admin 1
INSERT INTO user (email, password, role, date_created, last_updated) 
VALUES ('admin@gmail.com', MD5('admin'), 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO admin (admin_uuid, u_id, department, country, date_created, last_updated) 
VALUES ('AD1', 1, 'OPS', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Admin 2
INSERT INTO user (email, password, role, date_created, last_updated) 
VALUES ('admin2@gmail.com', MD5('admin2'), 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO admin (admin_uuid, u_id, department, country, date_created, last_updated) 
VALUES ('AD2', 2, 'Legal', 'Malaysia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Approver 1
INSERT INTO user (email, password, role, date_created, last_updated) 
VALUES ('approver@gmail.com', MD5('approver'), 'approver', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO approver (approver_uuid, u_id, approval_tier, date_created, last_updated) 
VALUES ('AP1', 3, '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Approver 2
INSERT INTO user (email, password, role, date_created, last_updated) 
VALUES ('approver2@gmail.com', MD5('approver2'), 'approver', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO approver (approver_uuid, u_id, approval_tier, date_created, last_updated) 
VALUES ('AP2', 4, '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Vendor 1
INSERT INTO user (email, password, role, date_created, last_updated) 
VALUES ('vendor@gmail.com', MD5('vendor'), 'vendor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vendor (vendor_uuid, u_id, company, country, date_created, last_updated) 
VALUES ('V1', 5, 'SMU', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Vendor 2
INSERT INTO user (email, password, role, date_created, last_updated) 
VALUES ('vendor2@gmail.com', MD5('vendor2'), 'vendor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vendor (vendor_uuid, u_id, company, country, date_created, last_updated) 
VALUES ('V2', 5, 'NTU', 'Singapore', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Form 1
INSERT INTO form (form_uuid, created_by, name, description, date_created, last_updated) 
VALUES ('F1', 'AD1', 'TestOne','This is a test form', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Form 2
INSERT INTO form (form_uuid, created_by, name, description, date_created, last_updated) 
VALUES ('F2', 'AD1', 'TestTwo','This is a test form', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Form 3
INSERT INTO form (form_uuid, created_by, name, description, date_created, last_updated) 
VALUES ('F3', 'AD1', 'TestThree','This is a test form', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Workflow 1
INSERT INTO workflow (workflow_uuid, name, description, created_by, date_created, last_updated) 
VALUES ('W1', 'TestOne', 'This is the first Test Workflow','AD1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Workflow 2
INSERT INTO workflow (workflow_uuid, name, description, created_by, date_created, last_updated) 
VALUES ('W2', 'TestTwo', 'This is the second Test Workflow','AD2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion of Assignment 1
INSERT INTO  vendor_workflow_assignment(workflow_uuid, vendor_uuid) 
VALUES ('W1', 'V1');

-- Insertion of Assignment 2
INSERT INTO  vendor_workflow_assignment(workflow_uuid, vendor_uuid) 
VALUES ('W1', 'V2');

-- Insertion of Step 1-9
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S1', 'W1', 'F1', '1', 'aStep', 'lorem lorem', '1', 'submit', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S2', 'W1', 'F2', '1', 'aStep', 'lorem lorem', '1', 'submit', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S3', 'W1', 'F3', '1', 'aStep', 'lorem lorem', '1', 'submit', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S4', 'W1', 'F1', '3', 'aStep', 'lorem lorem', '2', 'check', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S5', 'W1', 'F2', '3', 'aStep', 'lorem lorem', '2', 'check', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S6', 'W1', 'F3', '3', 'aStep', 'lorem lorem', '2', 'check', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S7', 'W1', 'F1', '5', 'aStep', 'lorem lorem', '3', 'approve', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S8', 'W1', 'F2', '5', 'aStep', 'lorem lorem', '3', 'approve', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  workflow_steps(step_uuid, parent_workflow, associated_form, assignee, name, description, order_no, action, date_created, last_updated) 
VALUES ('S9', 'W1', 'F3', '5', 'aStep', 'lorem lorem', '3', 'approve', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated) 
VALUES ('C1', 'F1', 'Name', 'text', TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated) 
VALUES ('C2', 'F1', 'Age', 'integer', TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated)
VALUES ('C3', 'F1', 'Skill Level', 'dropdown', TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  form_component(component_uuid, parent_form, question, type, is_required, order_no, date_created, last_updated)
VALUES ('C4', 'F1', 'Gender', 'checkbox', TRUE, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('O1', 'Male', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('O2', 'Female', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('O3', 'Low', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('O4', 'Medium', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  options(option_uuid, option_prompt, date_created, last_updated) 
VALUES ('O5', 'High', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('C3', 'O3');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('C3', 'O4');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('C3', 'O5');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('C4', 'O1');
INSERT INTO  option_component_link(component_uuid, option_uuid) 
VALUES ('C4', 'O2');

INSERT INTO  application(application_uuid, form_uuid, submitted_by, status, date_created, last_updated) 
VALUES ('APP1', 'F1', '1', 'inProgress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application(application_uuid, form_uuid, submitted_by, status, date_created, last_updated) 
VALUES ('APP2', 'F2', '1', 'inProgress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('APP1', 'C1', 'Jeff', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('APP1', 'C2', '21', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('APP1', 'C3', '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO  application_response_value(application_uuid, component_uuid, value, date_created, last_updated) 
VALUES ('APP1', 'C4', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
