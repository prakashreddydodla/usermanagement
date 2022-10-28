--------- Plan Details--------------

INSERT INTO public.plan_details
(id, created_by, created_date, last_modified_date, modified_by, description, max_users, plan_name)
VALUES(287, 123, '2022-08-25 18:30:08.194', '2022-08-25 18:30:08.194', 321, 'EasyRetailPlans', 3, 'Basic');
INSERT INTO public.plan_details
(id, created_by, created_date, last_modified_date, modified_by, description, max_users, plan_name)
VALUES(291, 123, '2022-08-25 18:30:08.414', '2022-08-25 18:30:08.414', 321, 'EasyRetailPlans', 5, 'Standard');
INSERT INTO public.plan_details
(id, created_by, created_date, last_modified_date, modified_by, description, max_users, plan_name)
VALUES(295, 123, '2022-08-25 18:30:08.457', '2022-08-25 18:30:08.457', 321, 'EasyRetailPlans', 9, 'Premium');

-----------Tenure Details---------

INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1225, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 99, 'OneMonth', 287);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1226, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 297, 'ThreeMonths', 287);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1227, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 594, 'SixMonths', 287);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1228, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 1188, 'OneYear', 287);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1230, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 499, 'OneMonth', 291);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1231, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 1497, 'ThreeMonths', 291);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1232, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 2994, 'SixMonths', 291);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1233, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 5988, 'OneYear', 291);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1235, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 999, 'OneMonth', 295);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1236, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 2997, 'ThreeMonths', 295);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1237, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 5994, 'SixMonths', 295);
INSERT INTO public.tenure_details
(id, created_by, created_date, last_modified_date, modified_by, amount, plan_tenure, plan_id)
VALUES(1238, 123, '2022-08-25 12:37:45.041', '2022-08-25 12:37:45.041', 321, 11988, 'OneYear', 295);

------------------------- parent parent_privilege----------------------

INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(600, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'URM Portal', 'icon-notes fs-30 i_icon', '/urmDashboard', NULL, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(690, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Dashboard', 'icon-cash_memo fs-30 i_icon', '/dashboard', NULL, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(692, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'URM Portal', 'icon-notes fs-30 i_icon', '/urmDashboard', NULL, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(500, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Ticketing Portal', 'icon-notes fs-30 i_icon', '/ticketingPortal', NULL, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(795, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Reports', 'icon-notes fs-30 i_icon', '/reportsDashboard', 291, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(619, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Inventory Portal', 'icon-notes fs-30 i_icon', '/inventoryList', 295, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(633, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Billing Portal', 'icon-cash_memo fs-30 i_icon', '/createdeliveryslip', 295, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(644, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', NULL, true, 'Reports', 'icon-notes fs-30 i_icon', '/reportsDashboard', 295, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(659, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Accounting Portal', 'icon-notes fs-30 i_icon', '/accountingDashboard', 295, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(675, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Promotions & Loyalty', 'icon-notes fs-30 i_icon', '/listOfPools', 295, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(711, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Inventory Portal', 'icon-notes fs-30 i_icon', '/inventoryList', 295, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(725, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Billing Portal', 'icon-cash_memo fs-30 i_icon', '/createdeliveryslip', 295, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(736, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', NULL, true, 'Reports', 'icon-notes fs-30 i_icon', '/reportsDashboard', 295, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(751, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Accounting Portal', 'icon-notes fs-30 i_icon', '/accountingDashboard', 295, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(767, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Promotions & Loyalty', 'icon-notes fs-30 i_icon', '/listOfPools', 295, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(790, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Inventory Portal', 'icon-notes fs-30 i_icon', '/inventoryList', 287, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(791, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Billing Portal', 'icon-notes fs-30 i_icon', '/createdeliveryslip', 287, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(792, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Accounting Portal', 'icon-notes fs-30 i_icon', '/accountingDashboard', 291, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(793, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Inventory Portal', 'icon-notes fs-30 i_icon', '/inventoryList', 291, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(794, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Billing Portal', 'icon-notes fs-30 i_icon', '/createdeliveryslip', 291, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(798, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Ticketing Portal', 'icon-notes fs-30 i_icon', '/ticketingPortal', 295, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(650, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Captain portal', 'icon-notes fs-30 i_icon', '/urmDashboard', NULL, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(598, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Dashboard', 'icon-cash_memo fs-30 i_icon', '/dashboard', 295, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(782, NULL, '2022-10-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Accounting Portal', 'icon-notes fs-30 i_icon', '/accountingDashboard', 287, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(783, NULL, '2022-10-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Inventory Portal', 'icon-notes fs-30 i_icon', '/inventoryList', 287, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(784, NULL, '2022-10-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Billing Portal', 'icon-notes fs-30 i_icon', '/createdeliveryslip', 287, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(785, NULL, '2022-09-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Accounting Portal', 'icon-notes fs-30 i_icon', '/accountingDashboard', 291, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(786, NULL, '2022-09-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Inventory Portal', 'icon-notes fs-30 i_icon', '/inventoryList', 291, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(787, NULL, '2022-09-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Billing Portal', 'icon-notes fs-30 i_icon', '/createdeliveryslip', 291, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(788, NULL, '2022-09-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Reports', 'icon-notes fs-30 i_icon', '/reportsDashboard', 291, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(789, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Accounting Portal', 'icon-notes fs-30 i_icon', '/accountingDashboard', 287, 'Mobile', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(799, NULL, '2022-10-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', NULL, false, 'Reports', 'icon-notes fs-30 i_icon', '/reportsDashboard', 287, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(800, NULL, '2022-10-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, false, 'Promotions & Loyalty', 'icon-notes fs-30 i_icon', '/listOfPools', 287, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(801, NULL, '2022-09-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, false, 'Promotions & Loyalty', 'icon-notes fs-30 i_icon', '/listOfPools', 291, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(796, NULL, '2022-10-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Ticketing Portal', 'icon-notes fs-30 i_icon', '/ticketingPortal', 287, 'Web', true, true);
INSERT INTO public.parent_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, "domain", is_active, "name", parent_image, "path", plan_id, previlege_type, "read", "write")
VALUES(797, NULL, '2022-09-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', NULL, true, 'Ticketing Portal', 'icon-notes fs-30 i_icon', '/ticketingPortal', 291, 'Web', true, true);

-------------sub previlege-------------


INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(800, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createHSN', 'Operation related to Users', NULL, 'Create HSN Code', 'Web', NULL, 785, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(847, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/taxreport', 'Operation related to Users', NULL, 'Tax Report', 'Web', NULL, 799, false, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(840, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-reports-dashboard', '/reportsDashboard', 'Operation related to Reports', NULL, 'Dashboard', 'Web', NULL, 799, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(841, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-list-of-estimation-slip', '/listofestimationslips', 'Operation related to Estimation Slips', NULL, 'List of Estimation Slips', 'Web', NULL, 799, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(842, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/salereport', 'Operation related to Users', NULL, 'New Sale Report', 'Web', NULL, 799, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(843, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listofsalebills', 'Operation related to Users', NULL, 'Goods Return', 'Web', NULL, 799, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(844, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofdeliveryslips', 'Operation related to Users', NULL, 'Sales Summary', 'Web', NULL, 799, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(845, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofreturnslips', 'Operation related to Users', NULL, 'List of Barcodes', 'Web', NULL, 799, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(846, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofPromotions', 'Operation related to Users', NULL, 'List of promotions', 'Web', NULL, 799, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(848, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listOfPools', 'Operation related to Pools', NULL, 'List of Pools', 'Web', NULL, 800, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(849, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listOfPromos', 'Operation related to Promotions', NULL, 'List Of Promotions', 'Web', NULL, 800, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(850, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/managePromo', 'Operation related to Promotions', NULL, 'Manage Promotions', 'Web', NULL, 800, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(851, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listOfPools', 'Operation related to Pools', NULL, 'List of Pools', 'Web', NULL, 801, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(852, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listOfPromos', 'Operation related to Promotions', NULL, 'List Of Promotions', 'Web', NULL, 801, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(853, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/managePromo', 'Operation related to Promotions', NULL, 'Manage Promotions', 'Web', NULL, 801, false, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(726, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createdeliveryslip', 'Operation related to Users', NULL, 'Generate Estimation Slip', 'Mobile', NULL, 725, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(727, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/newsale', 'Operation related to Users', NULL, 'Generate Invoice', 'Mobile', NULL, 725, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(731, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-promo', '/generatereturnslip', 'Operation related to Return Slip', NULL, 'Generate Return Slip', 'Mobile', NULL, 725, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(732, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_customer', '/createcustomer', 'Operation related to Users', NULL, 'Add Customer', 'Mobile', NULL, 725, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(733, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-tag_customer', '/tagcustomer', 'Operation related to Users', NULL, 'Gift Voucher', 'Mobile', NULL, 725, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(735, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-close', '/posdayclose', 'Operation related to Users', NULL, 'Day Closure Activity', 'Mobile', NULL, 725, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(737, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-reports-dashboard', '/reportsDashboard', 'Operation related to Reports', NULL, 'Dashboard', 'Mobile', NULL, 736, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(738, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-list-of-estimation-slip', '/listofestimationslips', 'Operation related to Estimation Slips', NULL, 'List of Estimation Slips', 'Mobile', NULL, 736, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(761, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createTaxMaster', 'Operation related to Users', NULL, 'Create Tax Master', 'Mobile', NULL, 751, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(854, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-productcombo', '/trackorder', 'Operation related to ProductCombo', NULL, 'Track Order', 'Web', NULL, 619, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(645, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-reports-dashboard', '/reportsDashboard', 'Operation related to Reports', NULL, 'Dashboard', 'Web', NULL, 644, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(650, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/salereport', 'Operation related to Users', NULL, 'New Sale Report', 'Web', NULL, 644, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(785, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createHSN', 'Operation related to Users', NULL, 'Create HSN Code', 'Mobile', NULL, 789, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(742, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/salereport', 'Operation related to Users', NULL, 'New Sale Report', 'Mobile', NULL, 736, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(746, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listofsalebills', 'Operation related to Users', NULL, 'Goods Return', 'Mobile', NULL, 736, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(747, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofdeliveryslips', 'Operation related to Users', NULL, 'Sales Summary', 'Mobile', NULL, 736, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(748, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofreturnslips', 'Operation related to Users', NULL, 'List of Barcodes', 'Mobile', NULL, 736, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(750, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofPromotions', 'Operation related to Users', NULL, 'List of promotions', 'Mobile', NULL, 736, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(752, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/accountingDashboard', 'Operation related to Users', NULL, 'Dashboard', 'Mobile', NULL, 751, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(753, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createNotes', 'Operation related to Users', NULL, 'Credit Notes', 'Mobile', NULL, 751, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(757, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/debitNotes', 'Operation related to Users', NULL, 'Debit Notes', 'Mobile', NULL, 751, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(768, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listOfPools', 'Operation related to Pools', NULL, 'List of Pools', 'Mobile', NULL, 767, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(772, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listOfPromos', 'Operation related to Promotions', NULL, 'List Of Promotions', 'Mobile', NULL, 767, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(776, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/managePromo', 'Operation related to Promotions', NULL, 'Manage Promotions', 'Mobile', NULL, 767, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(777, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/adminDetails', 'Operation related to users', NULL, 'Admin Details', 'Web', 'captain', 650, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(778, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/clientDetails', 'Operation related to users', NULL, 'Client Details', 'Web', 'captain', 650, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(779, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/clientSupportDetails', 'Operation related to users', NULL, 'Client Support Details', 'Web', 'captain', 650, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(780, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/clientMapping', 'Operation related to users', NULL, 'Client Mapping', 'Web', 'captain', 650, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(781, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/totalTickets', 'Operation related to users', NULL, 'Total Tickets', 'Web', 'ticketing_person', 500, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(782, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/openTickets', 'Operation related to users', NULL, 'Open Tickets', 'Web', 'ticketIng_person', 500, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(783, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/closedTickets', 'Operation related to users', NULL, 'Closed Tickets', 'Web', 'ticketIng_person', 500, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(786, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createTaxMaster', 'Operation related to Users', NULL, 'Create Tax Master', 'Web', NULL, 782, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(787, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createHSN', 'Operation related to Users', NULL, 'Create HSN Code', 'Web', NULL, 782, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(788, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createTaxMaster', 'Operation related to Users', NULL, 'create  TaxMaster', 'Mobile', NULL, 789, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(789, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/barcodeList', 'Operation related to Barcodes', NULL, 'Barcode List', 'Web', NULL, 783, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(790, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-re-barcode_list', '/rebarcoding', 'Operation related to Re-barcode', NULL, 'Re-Barcode List', 'Web', NULL, 783, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(791, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/barcodeList', 'Operation related to Barcodes', NULL, 'Barcode List', 'Mobile', NULL, 790, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(792, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-re-barcode_list', '/rebarcoding', 'Operation related to Re-barcode', NULL, 'Re-Barcode List', 'Mobile', NULL, 790, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(793, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createdeliveryslip', 'Operation related to Users', NULL, 'Generate Estimation Slip', 'Web', NULL, 784, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(794, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/newsale', 'Operation related to Users', NULL, 'Generate Invoice', 'Web', NULL, 784, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(795, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createdeliveryslip', 'Operation related to Users', NULL, 'Generate Estimation Slip', 'Mobile', NULL, 791, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(801, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/barcodeList', 'Operation related to Barcodes', NULL, 'Barcode List', 'Web', NULL, 786, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(802, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-re-barcode_list', '/rebarcoding', 'Operation related to Re-barcode', NULL, 'Re-Barcode List', 'Web', NULL, 786, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(803, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/barcodeList', 'Operation related to Barcodes', NULL, 'Barcode List', 'Mobile', NULL, 793, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(765, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createHSN', 'Operation related to Users', NULL, 'Create HSN Code', 'Mobile', NULL, 751, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(796, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/newsale', 'Operation related to Users', NULL, 'Generate Invoice', 'Mobile', NULL, 791, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(797, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createTaxMaster', 'Operation related to Users', NULL, 'Create Tax Master', 'Mobile', NULL, 792, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(798, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createHSN', 'Operation related to Users', NULL, 'Create HSN Code', 'Mobile', NULL, 792, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(799, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createTaxMaster', 'Operation related to Users', NULL, 'Create Tax Master', 'Web', NULL, 785, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(855, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-productcombo', '/trackorder', 'Operation related to ProductCombo', NULL, 'Track Order', 'Web', NULL, 783, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(856, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-productcombo', '/trackorder', 'Operation related to ProductCombo', NULL, 'Track Order', 'Web', NULL, 786, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(614, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/stores', 'Operation related to Users', NULL, 'Stores', 'Web', NULL, 600, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(701, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/roles', 'Operation related to Users', NULL, 'Roles', 'Mobile', NULL, 692, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(706, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/stores', 'Operation related to Users', NULL, 'Stores', 'Mobile', NULL, 692, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(712, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/barcodeList', 'Operation related to Barcodes', NULL, 'Barcode List', 'Mobile', NULL, 711, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(839, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/dashboard', 'Operation related to Users', NULL, 'Dashboard', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(599, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/dashboard', 'Operation related to Users', NULL, 'Dashboard', 'Web', NULL, 598, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(669, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createTaxMaster', 'Operation related to Users', NULL, 'Create Tax Master', 'Web', NULL, 659, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(673, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/createHSN', 'Operation related to Users', NULL, 'Create HSN Code', 'Web', NULL, 659, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(604, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/users', 'Operation related to Users', NULL, 'Users', 'Web', NULL, 600, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(609, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/roles', 'Operation related to Users', NULL, 'Roles', 'Web', NULL, 600, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(620, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/barcodeList', 'Operation related to Barcodes', NULL, 'Barcode List', 'Web', NULL, 619, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(625, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-re-barcode_list', '/rebarcoding', 'Operation related to Re-barcode', NULL, 'Re-Barcode List', 'Web', NULL, 619, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(628, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-productcombo', '/productsCombo', 'Operation related to ProductCombo', NULL, 'Product Combo', 'Web', NULL, 619, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(634, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createdeliveryslip', 'Operation related to Users', NULL, 'Generate Estimation Slip', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(635, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/newsale', 'Operation related to Users', NULL, 'Generate Invoice', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(639, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-promo', '/generatereturnslip', 'Operation related to Return Slip', NULL, 'Generate Return Slip', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(640, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_customer', '/createcustomer', 'Operation related to Users', NULL, 'Add Customer', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(641, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-tag_customer', '/tagcustomer', 'Operation related to Users', NULL, 'Gift Voucher', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(643, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-close', '/posdayclose', 'Operation related to Users', NULL, 'Day Closure Activity', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(646, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-list-of-estimation-slip', '/listofestimationslips', 'Operation related to Estimation Slips', NULL, 'List of Estimation Slips', 'Web', NULL, 644, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(654, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listofsalebills', 'Operation related to Users', NULL, 'Goods Return', 'Web', NULL, 644, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(655, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofdeliveryslips', 'Operation related to Users', NULL, 'Sales Summary', 'Web', NULL, 644, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(656, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofreturnslips', 'Operation related to Users', NULL, 'List of Barcodes', 'Web', NULL, 644, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(658, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofPromotions', 'Operation related to Users', NULL, 'List of promotions', 'Web', NULL, 644, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(660, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/accountingDashboard', 'Operation related to Users', NULL, 'Dashboard', 'Web', NULL, 659, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(661, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createNotes', 'Operation related to Users', NULL, 'Credit Notes', 'Web', NULL, 659, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(665, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/debitNotes', 'Operation related to Users', NULL, 'Debit Notes', 'Web', NULL, 659, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(676, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listOfPools', 'Operation related to Pools', NULL, 'List of Pools', 'Web', NULL, 675, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(680, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listOfPromos', 'Operation related to Promotions', NULL, 'List Of Promotions', 'Web', NULL, 675, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(684, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/managePromo', 'Operation related to Promotions', NULL, 'Manage Promotions', 'Web', NULL, 675, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(691, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/dashboard', 'Operation related to Users', NULL, 'Dashboard', 'Mobile', NULL, 690, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(693, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/urmDashboard', 'Operation related to Users', NULL, 'Dashboard', 'Mobile', NULL, 692, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(696, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/users', 'Operation related to Users', NULL, 'Users', 'Mobile', NULL, 692, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(717, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-re-barcode_list', '/rebarcoding', 'Operation related to Re-barcode', NULL, 'Re-Barcode List', 'Mobile', NULL, 711, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(720, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-productcombo', '/productsCombo', 'Operation related to ProductCombo', NULL, 'Product Combo', 'Mobile', NULL, 711, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(804, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-re-barcode_list', '/rebarcoding', 'Operation related to Re-barcode', NULL, 'Re-Barcode List', 'Mobile', NULL, 793, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(805, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createdeliveryslip', 'Operation related to Users', NULL, 'Generate Estimation Slip', 'Mobile', NULL, 794, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(806, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/newsale', 'Operation related to Users', NULL, 'Generate Invoice', 'Mobile', NULL, 794, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(807, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-promo', '/generatereturnslip', 'Operation related to Return Slip', NULL, 'Generate Return Slip', 'Mobile', NULL, 794, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(808, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-close', '/posdayclose', 'Operation related to Users', NULL, 'Day Closure Activity', 'Mobile', NULL, 794, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(809, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/createdeliveryslip', 'Operation related to Users', NULL, 'Generate Estimation Slip', 'Web', NULL, 787, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(810, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/newsale', 'Operation related to Users', NULL, 'Generate Invoice', 'Web', NULL, 787, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(811, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-promo', '/generatereturnslip', 'Operation related to Return Slip', NULL, 'Generate Return Slip', 'Web', NULL, 787, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(812, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-close', '/posdayclose', 'Operation related to Users', NULL, 'Day Closure Activity', 'Web', NULL, 787, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(813, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-list-of-estimation-slip', '/listofestimationslips', 'Operation related to Estimation Slips', NULL, 'List of Estimation Slips', 'Web', NULL, 788, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(814, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/salereport', 'Operation related to Users', NULL, 'New Sale Report', 'Web', NULL, 788, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(815, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listofsalebills', 'Operation related to Users', NULL, 'Goods Return', 'Web', NULL, 788, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(816, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofdeliveryslips', 'Operation related to Users', NULL, 'Sales Summary', 'Web', NULL, 788, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(817, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofreturnslips', 'Operation related to Users', NULL, 'List of Barcodes', 'Web', NULL, 788, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(818, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-list-of-estimation-slip', '/listofestimationslips', 'Operation related to Estimation Slips', NULL, 'List of Estimation Slips', 'Mobile', NULL, 795, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(819, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/salereport', 'Operation related to Users', NULL, 'New Sale Report', 'Mobile', NULL, 795, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(820, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/listofsalebills', 'Operation related to Users', NULL, 'Goods Return', 'Mobile', NULL, 795, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(821, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofdeliveryslips', 'Operation related to Users', NULL, 'Sales Summary', 'Mobile', NULL, 795, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(822, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/listofreturnslips', 'Operation related to Users', NULL, 'List of Barcodes', 'Mobile', NULL, 795, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(823, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/ticketDetails', 'Operation related to Users', NULL, 'Ticket Details', 'Web', NULL, 500, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(824, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/ticketDetails', 'Operation related to users', NULL, 'Ticket Details', 'Web', NULL, 796, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(825, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/ticketDetails', 'Operation related to users', NULL, 'Ticket Details', 'Web', NULL, 797, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(826, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/ticketDetails', 'Operation related to users', NULL, 'Ticket Details', 'Web', NULL, 798, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(830, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-sale', '/goodstransfer', 'Operation related to Barcodes', NULL, 'Goods Transfer', 'Web', NULL, 619, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(831, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-re-barcode_list', '/ordershipment', 'Operation related to Re-barcode', NULL, 'Order Shipment', 'Web', NULL, 619, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(832, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-productcombo', '/receiveorder', 'Operation related to ProductCombo', NULL, 'Receive Order', 'Web', NULL, 619, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(837, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/loyaltypoints', 'Operation related to Users', NULL, 'Loyalty points', 'Web', NULL, 633, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(838, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/loyaltypoints', 'Operation related to Users', NULL, 'Loyalty points', 'Mobile', NULL, 725, true, 'taxrequired');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(835, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/taxreport', 'Operation related to Users', NULL, 'Tax Report', 'Web', NULL, 644, true, 'taxNotApplicable');
INSERT INTO public.sub_privilege
(id, created_by, created_date, last_modified_date, modified_by, child_image, child_path, description, "domain", "name", previlege_type, role_name, parent_privilege_id, is_active, is_tax_applicable)
VALUES(836, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'icon-create_delivery_slip', '/taxreport', 'Operation related to Users', NULL, 'Tax Report', 'Web', NULL, 788, true, 'taxNotApplicable');



------------ child privileges---------

INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(782, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Add Tax Master', 'Web', 'icon-create_tax_master', '/createtaxmaster', 786);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(783, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Edit Tax Master', 'Web', 'icon-edit_tax_master', '/edittaxmaster', 786);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(784, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Delete Tax Master', 'Web', 'icon-delete_tax_master', '/deletetaxmaster', 786);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(785, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Add Tax Master', 'Mobile', 'icon-create_tax_master', '/createtaxmaster', 788);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(786, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Edit Tax Master', 'Mobile', 'icon-edit_tax_master', '/edittaxmaster', 788);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(787, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Delete Tax Master', 'Mobile', 'icon-delete_tax_master', '/deletetaxmaster', 788);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(788, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Add Tax Master', 'Web', 'icon-create_tax_master', '/createtaxmaster', 799);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(789, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Edit Tax Master', 'Web', 'icon-edit_tax_master', '/edittaxmaster', 799);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(790, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Delete Tax Master', 'Web', 'icon-delete_tax_master', '/deletetaxmaster', 799);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(791, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Add Tax Master', 'Mobile', 'icon-create_tax_master', '/createtaxmaster', 797);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(792, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Edit Tax Master', 'Mobile', 'icon-edit_tax_master', '/edittaxmaster', 797);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(793, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Delete Tax Master', 'Mobile', 'icon-delete_tax_master', '/deletetaxmaster', 797);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(794, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Hsn Code', 0, false, 'Add Hsn Code', 'Web', 'icon-create_hsn_code', '/createhsncode', 787);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(795, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Hsn Code', 0, false, 'Add Hsn Code', 'Mobile', 'icon-create_hsn_code', '/createhsncode', 785);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(796, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Hsn Code', 0, false, 'Add Hsn Code', 'Web', 'icon-create_hsn_code', '/createhsncode', 800);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(797, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Hsn Code', 0, false, 'Add Hsn Code', 'Mobile', 'icon-create_hsn_code', '/createhsncode', 798);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(798, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Add Barcode', 'Web', 'icon-create_barcode', '/createbarcode', 789);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(799, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Edit Barcode', 'Web', 'icon-edit_barcode', '/editbarcode', 789);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(800, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Delete Barcode', 'Web', 'icon-delete_barcode', '/deletebarcode', 789);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(801, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'View', 'Web', 'icon-view-barcodes', '/viewbarcodes', 789);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(802, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Add Barcode', 'Mobile', 'icon-create_barcode', '/createbarcode', 791);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(803, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Edit Barcode', 'Mobile', 'icon-edit_barcode', '/editbarcode', 791);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(804, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Delete Barcode', 'Mobile', 'icon-delete_barcode', '/deletebarcode', 791);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(805, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'View', 'Mobile', 'icon-view-barcodes', '/viewbarcodes', 791);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(806, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Add Barcode', 'Web', 'icon-create_barcode', '/createbarcode', 801);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(807, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Edit Barcode', 'Web', 'icon-edit_barcode', '/editbarcode', 801);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(808, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Delete Barcode', 'Web', 'icon-delete_barcode', '/deletebarcode', 801);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(809, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'View', 'Web', 'icon-view-barcodes', '/viewbarcodes', 801);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(810, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Add Barcode', 'Mobile', 'icon-create_barcode', '/createbarcode', 803);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(811, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Edit Barcode', 'Mobile', 'icon-edit_barcode', '/editbarcode', 803);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(812, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Delete Barcode', 'Mobile', 'icon-delete_barcode', '/deletebarcode', 803);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(813, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'View', 'Mobile', 'icon-view-barcodes', '/viewbarcodes', 803);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(814, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'ViewRe-Barcode', 'Web', 'icon-view-rebarcode', '/viewrebarcode', 790);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(815, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'Print', 'Web', 'icon-print', '/print', 790);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(816, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'ViewRe-Barcode', 'Mobile', 'icon-view-rebarcode', '/viewrebarcode', 792);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(817, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'Print', 'Mobile', 'icon-print', '/print', 792);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(818, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'ViewRe-Barcode', 'Web', 'icon-view-rebarcode', '/viewrebarcode', 802);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(819, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'Print', 'Web', 'icon-print', '/print', 802);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(820, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'ViewRe-Barcode', 'Mobile', 'icon-view-rebarcode', '/viewrebarcode', 804);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(821, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'Print', 'Mobile', 'icon-print', '/print', 804);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(823, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Tag Customer', 'Web', 'icon-tag-customer', '/tagcustomer', 794);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(824, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Bill Level Discount', 'Web', 'icon-bill-level-discount', '/billleveldiscount', 794);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(825, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Check Promo Discount', 'Web', 'icon-check-promo-discount', '/checkpromodiscount', 794);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(826, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Tag Customer', 'Mobile', 'icon-tag-customer', '/tagcustomer', 796);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(827, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Bill Level Discount', 'Mobile', 'icon-bill-level-discount', '/billleveldiscount', 796);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(828, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Check Promo Discount', 'Mobile', 'icon-check-promo-discount', '/checkpromodiscount', 796);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(829, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Tag Customer', 'Web', 'icon-tag-customer', '/tagcustomer', 810);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(830, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Bill Level Discount', 'Web', 'icon-bill-level-discount', '/billleveldiscount', 810);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(831, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Check Promo Discount', 'Web', 'icon-check-promo-discount', '/checkpromodiscount', 810);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(832, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Tag Customer', 'Mobile', 'icon-tag-customer', '/tagcustomer', 806);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(833, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Bill Level Discount', 'Mobile', 'icon-bill-level-discount', '/billleveldiscount', 806);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(834, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Check Promo Discount', 'Mobile', 'icon-check-promo-discount', '/checkpromodiscount', 806);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(835, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to ListOfBarcodes', 0, false, 'View', 'Web', 'icon-view', '/view', 817);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(836, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to ListOfBarcodes', 0, false, 'View', 'Mobile', 'icon-view', '/view', 822);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(837, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Print', 'Web', 'icon-print', '/print', 813);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(838, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'View', 'Web', 'icon-view', '/view', 813);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(839, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Delete', 'Web', 'icon-delete', '/delete', 813);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(840, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Print', 'Mobile', 'icon-print', '/print', 818);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(841, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'View', 'Mobile', 'icon-view', '/view', 818);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(842, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Delete', 'Mobile', 'icon-delete', '/delete', 818);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(843, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Print', 'Web', 'icon-print', '/print', 814);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(844, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'View', 'Web', 'icon-view', '/view', 814);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(845, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Delete', 'Web', 'icon-delete', '/delete', 814);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(846, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Print', 'Mobile', 'icon-print', '/print', 819);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(847, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'View', 'Mobile', 'icon-view', '/view', 819);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(848, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Delete', 'Mobile', 'icon-delete', '/delete', 819);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(652, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'View', 'Web', 'icon-view', '/view', 650);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(744, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'View', 'Mobile', 'icon-view', '/view', 742);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(763, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Edit Tax Master', 'Mobile', 'icon-edit_tax_master', '/edittaxmaster', 761);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(649, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Delete', 'Web', 'icon-delete', '/delete', 646);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(770, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Pools', 0, false, 'Edit Pool', 'Mobile', 'icon-edit_pool', '/editpool', 768);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(739, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Print', 'Mobile', 'icon-print', '/print', 738);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(647, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Print', 'Web', 'icon-print', '/print', 646);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(760, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to debit Notes', 0, false, 'Delete debit Notes', 'Mobile', 'icon-delete_debit_notes', '/deletedebitnotes', 757);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(636, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Tag Customer', 'Web', 'icon-tag-customer', '/tagcustomer', 635);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(663, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Credit Notes', 0, false, 'Edit Credit Notes', 'Web', 'icon-edit_credit_notes', '/editcreditnotes', 661);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(657, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to ListOfBarcodes', 0, false, 'View', 'Web', 'icon-view', '/view', 656);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(749, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to ListOfBarcodes', 0, false, 'View', 'Mobile', 'icon-view', '/view', 748);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(780, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Change Promo Priority', 'Mobile', 'icon-change-promo-priority', '/changepromopriority', 776);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(721, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'Add Product Combo', 'Mobile', 'icon-create_product_combo', '/createproductcombo', 720);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(638, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Check Promo Discount', 'Web', 'icon-check-promo-discount', '/checkpromodiscount', 635);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(674, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Hsn Code', 0, false, 'Add Hsn Code', 'Web', 'icon-create_hsn_code', '/createhsncode', 673);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(730, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Check Promo Discount', 'Mobile', 'icon-check-promo-discount', '/checkpromodiscount', 727);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(671, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Edit Tax Master', 'Web', 'icon-edit_tax_master', '/edittaxmaster', 669);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(766, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Hsn Code', 0, false, 'Add Hsn Code', 'Mobile', 'icon-create_hsn_code', '/createhsncode', 765);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(626, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'ViewRe-Barcode', 'Web', 'icon-view-rebarcode', '/viewrebarcode', 625);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(773, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Add Promotion', 'Mobile', 'icon-create_promotions', '/createpromo', 772);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(741, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'Delete', 'Mobile', 'icon-delete', '/delete', 738);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(666, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to debit Notes', 0, false, 'Add debit Notes', 'Web', 'icon-create_debit_notes', '/createdebitnotes', 665);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(775, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'View Promotions', 'Mobile', 'icon-view_promotionz', '/viewpromotions', 772);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(631, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'Delete Product Combo', 'Web', 'icon-delete_product_combo', '/deleteproductcombo', 628);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(728, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Tag Customer', 'Mobile', 'icon-tag-customer', '/tagcustomer', 727);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(629, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'Add Product Combo', 'Web', 'icon-create_product_combo', '/createproductcombo', 628);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(668, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to debit Notes', 0, false, 'Delete debit Notes', 'Web', 'icon-delete_debit_notes', '/deletedebitnotes', 665);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(603, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Domains and Clients', 0, false, 'Add Domain To Client', 'Web', 'icon-create_domain', '/createdomain', 602);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(704, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Roles', 0, false, 'Delete Role', 'Mobile', 'icon-delete_role', '/deleterole', 701);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(700, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'View', 'Mobile', 'icon-view-users', '/viewusers', 696);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(605, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'Add User', 'Web', 'icon-create_user', '/createuser', 604);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(713, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Add Barcode', 'Mobile', 'icon-create_barcode', '/createbarcode', 712);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(607, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'Delete User', 'Web', 'icon-delete_user', '/deleteuser', 604);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(707, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Stores', 0, false, 'Add Store', 'Mobile', 'icon-create_store', '/createstore', 706);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(698, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'Edit User', 'Mobile', 'icon-edit_user', '/edituser', 696);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(612, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Roles', 0, false, 'Delete Role', 'Web', 'icon-delete_role', '/deleterole', 609);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(688, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Change Promo Priority', 'Web', 'icon-change-promo-priority', '/changepromopriority', 684);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(695, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Domains and Clients', 0, false, 'Add Domain To Client', 'Mobile', 'icon-create_domain', '/createdomain', 694);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(723, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'Delete Product Combo', 'Mobile', 'icon-delete_product_combo', '/deleteproductcombo', 720);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(678, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Pools', 0, false, 'Edit Pool', 'Web', 'icon-edit_pool', '/editpool', 676);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(610, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Roles', 0, false, 'Add Role', 'Web', 'icon-create_role', '/createrole', 609);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(623, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Delete Barcode', 'Web', 'icon-delete_barcode', '/deletebarcode', 620);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(715, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Delete Barcode', 'Mobile', 'icon-delete_barcode', '/deletebarcode', 712);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(778, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Modify Validity', 'Mobile', 'icon-modify-validity', '/modifyvalidity', 776);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(681, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Add Promotion', 'Web', 'icon-create_promotions', '/createpromo', 680);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(709, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Stores', 0, false, 'Delete Store', 'Mobile', 'icon-delete_store', '/deletestore', 706);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(621, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Add Barcode', 'Web', 'icon-create_barcode', '/createbarcode', 620);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(686, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Modify Validity', 'Web', 'icon-modify-validity', '/modifyvalidity', 684);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(718, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'ViewRe-Barcode', 'Mobile', 'icon-view-rebarcode', '/viewrebarcode', 717);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(683, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'View Promotions', 'Web', 'icon-view_promotionz', '/viewpromotions', 680);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(615, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Stores', 0, false, 'Add Store', 'Web', 'icon-create_store', '/createstore', 614);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(617, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Stores', 0, false, 'Delete Store', 'Web', 'icon-delete_store', '/deletestore', 614);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(756, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Credit Notes', 0, false, 'Delete Credit Notes', 'Mobile', 'icon-delete_credit_notes', '/deletecreditnotes', 753);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(754, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Credit Notes', 0, false, 'Add Credit Notes', 'Mobile', 'icon-create_credit_notes', '/createcreditnotes', 753);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(651, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Print', 'Web', 'icon-print', '/print', 650);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(762, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Add Tax Master', 'Mobile', 'icon-create_tax_master', '/createtaxmaster', 761);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(662, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Credit Notes', 0, false, 'Add Credit Notes', 'Web', 'icon-create_credit_notes', '/createcreditnotes', 661);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(653, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Delete', 'Web', 'icon-delete', '/delete', 650);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(743, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Print', 'Mobile', 'icon-print', '/print', 742);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(734, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to gift voucher', 0, false, 'Add Gift Voucher', 'Mobile', 'icon-save-gift-voucher', '/savegiftvoucher', 733);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(667, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to debit Notes', 0, false, 'Edit debit Notes', 'Web', 'icon-edit_debit_notes', '/editdebitnotes', 665);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(648, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'View', 'Web', 'icon-view', '/view', 646);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(745, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Reports', 0, false, 'Delete', 'Mobile', 'icon-delete', '/delete', 742);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(759, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to debit Notes', 0, false, 'Edit debit Notes', 'Mobile', 'icon-edit_debit_notes', '/editdebitnotes', 757);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(637, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Bill Level Discount', 'Web', 'icon-bill-level-discount', '/billleveldiscount', 635);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(664, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Credit Notes', 0, false, 'Delete Credit Notes', 'Web', 'icon-delete_credit_notes', '/deletecreditnotes', 661);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(642, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to gift voucher', 0, false, 'Add Gift Voucher', 'Web', 'icon-save-gift-voucher', '/savegiftvoucher', 641);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(679, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Pools', 0, false, 'View Pools', 'Web', 'icon-view_pools', '/viewpools', 676);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(729, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Invoice', 0, false, 'Bill Level Discount', 'Mobile', 'icon-bill-level-discount', '/billleveldiscount', 727);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(624, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'View', 'Web', 'icon-view-barcodes', '/viewbarcodes', 620);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(764, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Delete Tax Master', 'Mobile', 'icon-delete_tax_master', '/deletetaxmaster', 761);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(740, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Estimation Slips', 0, false, 'View', 'Mobile', 'icon-view', '/view', 738);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(627, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'Print', 'Web', 'icon-print', '/print', 625);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(774, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotion', 0, false, 'Edit Promotion', 'Mobile', 'icon-edit_promotion', '/editpromotion', 772);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(677, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Pools', 0, false, 'Add Pool', 'Web', 'icon-create_pool', '/createpool', 676);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(724, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'View', 'Mobile', 'icon-view-products', '/viewproducts', 720);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(771, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Pools', 0, false, 'View Pools', 'Mobile', 'icon-view_pools', '/viewpools', 768);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(670, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Add Tax Master', 'Web', 'icon-create_tax_master', '/createtaxmaster', 669);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(769, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Pools', 0, false, 'Add Pool', 'Mobile', 'icon-create_pool', '/createpool', 768);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(632, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'View', 'Web', 'icon-view-products', '/viewproducts', 628);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(672, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Tax Master', 0, false, 'Delete Tax Master', 'Web', 'icon-delete_tax_master', '/deletetaxmaster', 669);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(630, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'Edit Product Combo', 'Web', 'icon-edit_product_combo', '/editproductcombo', 628);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(702, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Roles', 0, false, 'Add Role', 'Mobile', 'icon-create_role', '/createrole', 701);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(703, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Roles', 0, false, 'Edit Role', 'Mobile', 'icon-edit_role', '/editrole', 701);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(699, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'Delete User', 'Mobile', 'icon-delete_user', '/deleteuser', 696);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(705, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to roles', 0, false, 'View', 'Mobile', 'icon-view-roles', '/viewroles', 701);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(606, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'Edit User', 'Web', 'icon-edit_user', '/edituser', 604);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(689, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'View', 'Web', 'icon-view-promos', '/viewpromos', 684);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(777, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Add To Store', 'Mobile', 'icon-create_promotions', '/addstore', 776);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(708, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Stores', 0, false, 'Edit Store', 'Mobile', 'icon-edit_store', '/editstore', 706);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(611, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Roles', 0, false, 'Edit Role', 'Web', 'icon-edit_role', '/editrole', 609);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(608, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'View', 'Web', 'icon-view-users', '/viewusers', 604);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(710, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to stores', 0, false, 'View', 'Mobile', 'icon-view-stores', '/viewstores', 706);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(697, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Users', 0, false, 'Add User', 'Mobile', 'icon-create_user', '/createuser', 696);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(779, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Clone', 'Mobile', 'icon-colne-promotions', '/clonepromotions', 776);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(682, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotion', 0, false, 'Edit Promotion', 'Web', 'icon-edit_promotion', '/editpromotion', 680);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(719, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Re-Barcode', 0, false, 'Print', 'Mobile', 'icon-print', '/print', 717);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(622, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Edit Barcode', 'Web', 'icon-edit_barcode', '/editbarcode', 620);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(722, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Product Combo', 0, false, 'Edit Product Combo', 'Mobile', 'icon-edit_product_combo', '/editproductcombo', 720);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(618, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to stores', 0, false, 'View', 'Web', 'icon-view-stores', '/viewstores', 614);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(613, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to roles', 0, false, 'View', 'Web', 'icon-view-roles', '/viewroles', 609);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(716, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'View', 'Mobile', 'icon-view-barcodes', '/viewbarcodes', 712);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(687, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Clone', 'Web', 'icon-colne-promotions', '/clonepromotions', 684);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(685, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'Add To Store', 'Web', 'icon-create_promotions', '/addstore', 684);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(781, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Promotions', 0, false, 'View', 'Mobile', 'icon-view-promos', '/viewpromos', 776);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(758, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to debit Notes', 0, false, 'Add debit Notes', 'Mobile', 'icon-create_debit_notes', '/createdebitnotes', 757);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(616, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Stores', 0, false, 'Edit Store', 'Web', 'icon-edit_store', '/editstore', 614);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(714, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Barcodes', 0, false, 'Edit Barcode', 'Mobile', 'icon-edit_barcode', '/editbarcode', 712);
INSERT INTO public.child_privilege
(id, created_by, created_date, last_modified_date, modified_by, description, domian, is_enabeld, "name", previlege_type, sub_child_image, sub_child_path, sub_privillage_id)
VALUES(755, NULL, '2022-06-02 00:00:00.000', '2022-06-02 00:00:00.000', NULL, 'Operation related to Credit Notes', 0, false, 'Edit Credit Notes', 'Mobile', 'icon-edit_credit_notes', '/editcreditnotes', 753);


-------- States------------

INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(328, NULL, NULL, NULL, NULL, 'Bengaluru', 'KA', 17, 'Karnataka');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(625, NULL, NULL, NULL, NULL, 'Chennai', 'TN', 32, 'Tamil Nadu');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(195, NULL, NULL, NULL, NULL, 'New Delhi', 'DL', 10, 'Delhi');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(506, NULL, NULL, NULL, NULL, 'Aizawl', 'MZ', 25, 'Mizoram');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(658, NULL, NULL, NULL, NULL, 'Hyderabad', 'TS', 33, 'Telangana');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(359, NULL, NULL, NULL, NULL, 'Thiruvananthapuram', 'KL', 18, 'Kerala');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(789, NULL, NULL, NULL, NULL, 'Kolkata', 'WB', 37, 'West Bengal');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(192, NULL, NULL, NULL, NULL, 'Daman', 'DD', 9, 'Daman and Diu');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(494, NULL, NULL, NULL, NULL, 'Shillong', 'ML', 24, 'Meghalaya');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(775, NULL, NULL, NULL, NULL, 'Dehradun', 'UK', 36, 'Uttarakhand');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(690, NULL, NULL, NULL, NULL, 'Agartala', 'TR', 34, 'Tripura');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(207, NULL, NULL, NULL, NULL, 'Panaji', 'GA', 11, 'Goa');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(699, NULL, NULL, NULL, NULL, 'Lucknow', 'UP', 35, 'Uttar Pradesh');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(477, NULL, NULL, NULL, NULL, 'Imphal', 'MN', 23, 'Manipur');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(190, NULL, NULL, NULL, NULL, 'Silvassa', 'DH', 8, 'Dadra and Nagar Haveli');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(65, NULL, NULL, NULL, NULL, 'Itanagar', 'AR', 3, 'Arunachal Pradesh');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(620, NULL, NULL, NULL, NULL, 'Gangtok', 'SK', 31, 'Sikkim');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(303, NULL, NULL, NULL, NULL, 'Ranchi', 'JH', 16, 'Jharkhand');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(515, NULL, NULL, NULL, NULL, 'Kohima', 'NL', 26, 'Nagaland');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(160, NULL, NULL, NULL, NULL, 'Chandigarh', 'CG', 6, 'Chandigarh');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(388, NULL, NULL, NULL, NULL, 'Bhopal', 'MP', 21, 'Madhya Pradesh');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(558, NULL, NULL, NULL, NULL, 'Puducherry', 'PY', 28, 'Puducherry');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(244, NULL, NULL, NULL, NULL, 'Chandigarh', 'HR', 13, 'Haryana');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(377, NULL, NULL, NULL, NULL, 'Kavaratti', 'LD', 20, 'Lakshadweep');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(121, NULL, NULL, NULL, NULL, 'Patna', 'BR', 5, 'Bihar');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(267, NULL, NULL, NULL, NULL, 'Shimla', 'HP', 14, 'Himachal Pradesh');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(563, NULL, NULL, NULL, NULL, 'Chandigarh', 'PB', 29, 'Punjab');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(374, NULL, NULL, NULL, NULL, 'Leh, Kargil', 'LA', 19, 'Ladakh');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(87, NULL, NULL, NULL, NULL, 'Dispur', 'AS', 4, 'Assam');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(280, NULL, NULL, NULL, NULL, 'Jammu (Winter), Srinagar (Summer)', 'JK', 15, 'Jammu and Kashmir');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(586, NULL, NULL, NULL, NULL, 'Jaipur', 'RJ', 30, 'Rajasthan');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(440, NULL, NULL, NULL, NULL, 'Mumbai', 'MH', 22, 'Maharashtra');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(527, NULL, NULL, NULL, NULL, 'Bhubaneswar', 'OR', 27, 'Odisha');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(210, NULL, NULL, NULL, NULL, 'Gandhinagar', 'GJ', 12, 'Gujarat');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(162, NULL, NULL, NULL, NULL, 'Bilaspur (Judiciary), Raipur', 'CH', 7, 'Chhattisgarh');
INSERT INTO public.states
(id, created_by, created_date, last_modified_date, modified_by, capital, state_code, state_id, state_name)
VALUES(51, NULL, NULL, NULL, NULL, 'Amaravati', 'AP', 2, 'Andhra Pradesh');

----------------- Districts-------------

INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(687, 29, 'Warangal (Rural)', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(437, 49, 'Ujjain', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(485, 8, 'Kamjong', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(800, 11, 'Kalimpong', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(351, 23, 'Raichur', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(275, 8, 'Mandi', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(622, 2, 'North Sikkim', 'SK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(81, 16, 'Anjaw', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(754, 55, 'Mau', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(218, 8, 'Botad', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(138, 17, 'Khagaria', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(483, 6, 'Jiribam', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(681, 23, 'Rangareddy', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(148, 27, 'Purnia (Purnea)', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(349, 21, 'Mandya', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(286, 6, 'Ganderbal', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(554, 27, 'Rayagada', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(79, 14, 'Lower Dibang Valley', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(619, 33, 'Udaipur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(534, 7, 'Cuttack', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(216, 6, 'Bharuch', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(733, 34, 'Gorakhpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(320, 17, 'Pakur', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(752, 53, 'Mainpuri', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(555, 28, 'Sambalpur', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(420, 32, 'Neemuch', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(152, 31, 'Saran', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(577, 14, 'Moga', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(347, 19, 'Kolar', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(683, 25, 'Siddipet', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(442, 2, 'Akola', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(134, 13, 'Jamui', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(254, 10, 'Kaithal', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(202, 7, 'Shahdara', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(740, 41, 'Jhansi', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(617, 31, 'Sri Ganganagar', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(322, 19, 'Ramgarh', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(77, 12, 'Upper Siang', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(444, 4, 'Aurangabad', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(685, 27, 'Vikarabad', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(579, 16, 'Nawanshahr (Shahid Bhagat Singh Nagar)', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(790, 1, 'Alipurduar', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(273, 6, 'Kullu', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(792, 3, 'Birbhum', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(294, 14, 'Poonch', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(146, 25, 'Nawada', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(613, 27, 'Rajsamand', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(73, 8, 'Upper Subansiri', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(725, 26, 'Faizabad', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(376, 2, 'Leh', 'LA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(326, 23, 'Simdega', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(85, 20, 'Tirap', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(536, 9, 'Dhenkanal', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(679, 21, 'Peddapalli', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(418, 30, 'Morena', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(623, 3, 'South Sikkim', 'SK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(206, 11, 'West Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(802, 13, 'Malda', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(744, 45, 'Kanshiram Nagar (Kasganj)', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(439, 51, 'Vidisha', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(626, 1, 'Ariyalur', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(575, 12, 'Ludhiana', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(392, 4, 'Ashoknagar', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(83, 18, 'Namsai', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(266, 22, 'Yamunanagar', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(256, 12, 'Kurukshetra', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(140, 19, 'Lakhisarai', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(432, 44, 'Sheopur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(479, 2, 'Chandel', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(532, 5, 'Bhadrak', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(226, 16, 'Junagadh', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(398, 10, 'Burhanpur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(161, 1, 'Chandigarh', 'CG');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(345, 17, 'Kalaburagi (Gulbarga)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(264, 20, 'Sirsa', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(666, 8, 'Kamareddy', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(761, 62, 'RaeBareli', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(490, 13, 'Tamenglong', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(530, 3, 'Balasore', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(798, 9, 'Howrah', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(604, 18, 'Jalore', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(379, 2, 'Amini', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(126, 5, 'Begusarai', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(324, 21, 'Sahibganj', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(75, 10, 'East Siang', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(615, 29, 'Sikar', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(212, 2, 'Amreli', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(677, 19, 'Nirmal', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(262, 18, 'Rewari', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(748, 49, 'Lalitpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(572, 9, 'Hoshiarpur', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(528, 1, 'Angul', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(436, 48, 'Tikamgarh', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(573, 10, 'Jalandhar', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(200, 5, 'North East  Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(94, 7, 'Chirang', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(632, 7, 'Erode', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(735, 36, 'Hapur (Panchsheel Nagar)', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(304, 1, 'Bokaro', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(132, 11, 'Gaya', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(258, 14, 'Mewat', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(335, 7, 'Chamarajanagar', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(269, 2, 'Chamba', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(517, 2, 'Kiphire', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(92, 5, 'Cachar', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(691, 1, 'Dhalai', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(794, 5, 'Cooch Behar', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(583, 20, 'Sahibzada Ajit Singh Nagar (Mohali)', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(421, 33, 'Panna', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(634, 9, 'Kanyakumari', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(672, 14, 'Mancherial', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(514, 8, 'Serchhip', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(804, 15, 'Nadia', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(337, 9, 'Chikkamagaluru (Chikmagalur)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(628, 3, 'Coimbatore', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(488, 11, 'Pherzawl', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(427, 39, 'Satna', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(502, 8, 'South West Khasi Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(750, 51, 'Maharajganj', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(214, 4, 'Aravalli', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(697, 7, 'Unakoti', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(368, 9, 'Malappuram', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(150, 29, 'Saharsa', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(67, 2, 'West Kameng', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(607, 21, 'Jodhpur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(198, 3, 'New Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(224, 14, 'Gir Somnath', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(154, 33, 'Sheohar', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(136, 15, 'Kaimur (Bhabua)', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(425, 37, 'Rewa', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(88, 1, 'Baksa', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(408, 20, 'Harda', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(759, 60, 'Pilibhit', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(695, 5, 'Sepahijala', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(333, 5, 'Bengaluru (Bangalore) Urban', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(284, 4, 'Budgam', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(773, 74, 'Unnao', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(341, 13, 'Dharwad', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(779, 4, 'Champawat', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(492, 15, 'Thoubal', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(156, 35, 'Siwan', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(693, 3, 'Khowai', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(423, 35, 'Rajgarh', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(581, 18, 'Patiala', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(510, 4, 'Lawngtlai', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(771, 72, 'Sonbhadra', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(361, 2, 'Ernakulam', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(239, 29, 'Surat', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(670, 12, 'Mahabubabad', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(673, 15, 'Medak', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(339, 11, 'Dakshina Kannada', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(193, 1, 'Daman', 'DD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(71, 6, 'Kra Daadi', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(611, 25, 'Pali', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(329, 1, 'Bagalkot', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(130, 9, 'Darbhanga', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(508, 2, 'Champhai', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(237, 27, 'Rajkot', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(609, 23, 'Kota', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(242, 32, 'Vadodara', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(69, 4, 'Papum Pare', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(414, 26, 'Khandwa', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(196, 1, 'Central Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(279, 12, 'Una', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(675, 17, 'Nagarkurnool', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(331, 3, 'Belagavi (Belgaum)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(731, 32, 'Ghazipur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(585, 22, 'Tarn Taran', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(505, 11, 'West Khasi Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(90, 3, 'Biswanath', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(652, 27, 'Tiruvallur', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(630, 5, 'Dharmapuri', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(500, 6, 'South Garo Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(434, 46, 'Sidhi', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(282, 2, 'Bandipore', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(653, 28, 'Tiruvannamalai', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(416, 28, 'Mandla', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(737, 38, 'Hathras', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(588, 2, 'Alwar', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(512, 6, 'Mamit', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(48, 1, 'Nicobar', 'AN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(567, 4, 'Faridkot', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(113, 26, 'Nalbari', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(717, 18, 'Bijnor', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(250, 6, 'Gurgaon', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(766, 67, 'Shahjahanpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(230, 20, 'Mehsana', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(650, 25, 'Tirunelveli', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(519, 4, 'Longleng', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(655, 30, 'Vellore', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(544, 17, 'Kendrapara', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(410, 22, 'Indore', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(177, 15, 'Kanker (North Bastar)', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(385, 8, 'Kalpeni', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(787, 12, 'Udham Singh Nagar', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(172, 10, 'Durg', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(110, 23, 'Majuli', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(648, 23, 'Thoothukudi (Tuticorin)', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(228, 18, 'Kheda (Nadiad)', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(764, 65, 'Sambhal (Bhim Nagar)', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(49, 2, 'North and Middle Andaman', 'AN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(383, 6, 'Kavaratti', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(448, 8, 'Chandrapur', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(296, 16, 'Rajouri', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(119, 32, 'Udalguri', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(470, 30, 'Satara', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(381, 4, 'Bithra', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(406, 18, 'Guna', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(288, 8, 'Kargil', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(52, 1, 'Anantapur', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(708, 9, 'Baghpat', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(308, 5, 'Dumka', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(240, 30, 'Surendranagar', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(343, 15, 'Hassan', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(115, 28, 'Sivasagar', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(424, 36, 'Ratlam', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(776, 1, 'Almora', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(712, 13, 'Banda', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(476, 36, 'Yavatmal', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(252, 8, 'Jhajjar', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(499, 5, 'Ri Bhoi', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(591, 5, 'Barmer', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(175, 13, 'Jashpur', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(521, 6, 'Mon', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(108, 21, 'Kokrajhar', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(644, 19, 'Salem', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(387, 10, 'Minicoy', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(310, 7, 'Garhwa', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(174, 12, 'Janjgir-Champa', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(542, 15, 'Kalahandi', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(758, 59, 'Muzaffarnagar', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(260, 16, 'Panchkula', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(222, 12, 'Devbhoomi Dwarka', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(474, 34, 'Wardha', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(714, 15, 'Bareilly', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(698, 8, 'West Tripura', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(593, 7, 'Bhilwara', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(56, 5, 'Krishna', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(659, 1, 'Adilabad', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(355, 27, 'Udupi', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(220, 10, 'Dahod', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(756, 57, 'Mirzapur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(565, 2, 'Barnala', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(457, 17, 'Mumbai City', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(104, 17, 'Kamrup Metropolitan', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(495, 1, 'East Garo Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(450, 10, 'Gadchiroli', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(306, 3, 'Deoghar', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(170, 8, 'Dantewada (South Bastar)', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(353, 25, 'Shivamogga (Shimoga)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(412, 24, 'Jhabua', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(179, 17, 'Korba', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(781, 6, 'Haridwar', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(522, 7, 'Peren', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(595, 9, 'Bundi', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(721, 22, 'Chitrakoot', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(452, 12, 'Hingoli', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(168, 6, 'Bijapur', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(106, 19, 'Karbi Anglong', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(571, 8, 'Gurdaspur', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(181, 19, 'Mahasamund', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(646, 21, 'Thanjavur', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(524, 9, 'Tuensang', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(430, 42, 'Shahdol', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(128, 7, 'Bhojpur', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(298, 18, 'Reasi', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(795, 6, 'Dakshin Dinajpur (South Dinajpur)', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(656, 31, 'Viluppuram', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(96, 9, 'Dhemaji', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(637, 12, 'Madurai', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(719, 20, 'Bulandshahr', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(569, 6, 'Fazilka', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(300, 20, 'Shopian', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(785, 10, 'Rudraprayag', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(366, 7, 'Kottayam', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(54, 3, 'East Godavari', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(64, 13, 'YSR Kadapa', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(469, 29, 'Sangli', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(336, 8, 'Chikballapur', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(661, 3, 'Hyderabad', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(601, 15, 'Hanumangarh', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(537, 10, 'Gajapati', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(166, 4, 'Bastar', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(364, 5, 'Kasaragod', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(314, 11, 'Hazaribag', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(302, 22, 'Udhampur', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(292, 12, 'Kupwara', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(403, 15, 'Dewas', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(467, 27, 'Raigad', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(183, 21, 'Narayanpur', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(603, 17, 'Jaisalmer', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(248, 4, 'Faridabad', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(362, 3, 'Idukki', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(122, 1, 'Araria', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(481, 4, 'Imphal East', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(158, 37, 'Vaishali', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(701, 2, 'Aligarh', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(667, 9, 'Karimnagar', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(639, 14, 'Namakkal', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(185, 23, 'Raipur', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(98, 11, 'Dibrugarh', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(246, 2, 'Bhiwani', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(770, 71, 'Sitapur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(455, 15, 'Kolhapur', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(234, 24, 'Panchmahal (Godhra)', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(730, 31, 'Ghaziabad', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(562, 4, 'Yanam', 'PY');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(270, 3, 'Hamirpur', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(783, 8, 'Pauri Garhwal', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(597, 11, 'Churu', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(117, 30, 'South Salmara-Mankachar', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(540, 13, 'Jajpur', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(327, 24, 'West Singhbhum', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(62, 11, 'Vizianagaram', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(724, 25, 'Etawah', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(535, 8, 'Deogarh', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(461, 21, 'Nandurbar', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(560, 2, 'Mahe', 'PY');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(290, 10, 'Kishtwar', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(710, 11, 'Ballia', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(401, 13, 'Damoh', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(312, 9, 'Godda', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(276, 9, 'Shimla', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(746, 47, 'Kushinagar (Padrauna)', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(102, 15, 'Hojai', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(552, 25, 'Nuapada', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(459, 19, 'Nagpur', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(124, 3, 'Aurangabad', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(395, 7, 'Betul', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(642, 17, 'Pudukkottai', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(189, 27, 'Surguja', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(557, 30, 'Sundargarh', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(446, 6, 'Bhandara', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(100, 13, 'Golaghat', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(805, 16, 'North 24 Parganas', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(472, 32, 'Solapur', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(58, 7, 'Nellore', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(640, 15, 'Nilgiris', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(742, 43, 'Kanpur Dehat', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(728, 29, 'Firozabad', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(316, 13, 'Khunti', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(272, 5, 'Kinnaur', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(211, 1, 'Ahmedabad', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(546, 19, 'Khordha', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(373, 14, 'Wayanad', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(807, 18, 'Purba Medinipur (East Medinipur)', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(548, 21, 'Malkangiri', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(60, 9, 'Srikakulam', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(318, 15, 'Latehar', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(463, 23, 'Osmanabad', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(371, 12, 'Thiruvananthapuram', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(703, 4, 'Ambedkar Nagar', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(143, 22, 'Munger (Monghyr)', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(205, 10, 'South West  Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(232, 22, 'Narmada (Rajpipla)', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(768, 69, 'Shravasti', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(550, 23, 'Nabarangpur', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(809, 20, 'South 24 Parganas', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(663, 5, 'Jangaon', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(599, 13, 'Dholpur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(187, 25, 'Sukma', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(141, 20, 'Madhepura', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(705, 6, 'Amroha (J.P. Nagar)', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(393, 5, 'Balaghat', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(164, 2, 'Baloda Bazar', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(621, 1, 'East Sikkim', 'SK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(80, 15, 'Dibang Valley', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(734, 35, 'Hamirpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(419, 31, 'Narsinghpur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(217, 7, 'Bhavnagar', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(753, 54, 'Mathura', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(578, 15, 'Muktsar', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(484, 7, 'Kakching', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(147, 26, 'Patna', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(443, 3, 'Amravati', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(688, 30, 'Warangal (Urban)', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(285, 5, 'Doda', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(810, 21, 'Uttar Dinajpur (North Dinajpur)', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(274, 7, 'Lahaul &amp; Spiti', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(350, 22, 'Mysuru (Mysore)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(84, 19, 'Changlang', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(82, 17, 'Lohit', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(739, 40, 'Jaunpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(531, 4, 'Bargarh', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(678, 20, 'Nizamabad', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(219, 9, 'Chhota Udepur', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(261, 17, 'Panipat', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(137, 16, 'Katihar', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(465, 25, 'Parbhani', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(486, 9, 'Kangpokpi', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(352, 24, 'Ramanagara', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(799, 10, 'Jalpaiguri', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(415, 27, 'Khargone', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(741, 42, 'Kannauj', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(375, 1, 'Kargil', 'LA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(533, 6, 'Boudh', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(509, 3, 'Kolasib', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(203, 8, 'South Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(321, 18, 'Palamu', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(556, 29, 'Sonepur', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(263, 19, 'Rohtak', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(674, 16, 'Medchal', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(197, 2, 'East Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(133, 12, 'Gopalganj', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(225, 15, 'Jamnagar', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(618, 32, 'Tonk', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(751, 52, 'Mahoba', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(89, 2, 'Barpeta', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(78, 13, 'Lower Siang', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(445, 5, 'Beed', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(348, 20, 'Koppal', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(580, 17, 'Pathankot', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(553, 26, 'Puri', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(726, 27, 'Farrukhabad', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(325, 22, 'Seraikela-Kharsawan', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(227, 17, 'Kachchh', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(139, 18, 'Kishanganj', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(680, 22, 'Rajanna Sircilla', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(801, 12, 'Kolkata', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(791, 2, 'Bankura', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(208, 1, 'North Goa', 'GA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(745, 46, 'Kaushambi', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(627, 2, 'Chennai', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(529, 2, 'Balangir', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(489, 12, 'Senapati', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(614, 28, 'Sawai Madhopur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(253, 9, 'Jind', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(747, 48, 'Lakhimpur - Kheri', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(417, 29, 'Mandsaur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(323, 20, 'Ranchi', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(624, 4, 'West Sikkim', 'SK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(186, 24, 'Rajnandgaon', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(389, 1, 'Agar Malwa', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(686, 28, 'Wanaparthy', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(145, 24, 'Nalanda', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(441, 1, 'Ahmednagar', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(487, 10, 'Noney', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(74, 9, 'West Siang', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(576, 13, 'Mansa', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(605, 19, 'Jhalawar', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(255, 11, 'Karnal', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(480, 3, 'Churachandpur', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(86, 21, 'Longding', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(749, 50, 'Lucknow', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(76, 11, 'Siang', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(616, 30, 'Sirohi', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(213, 3, 'Anand', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(665, 7, 'Jogulamba Gadwal', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(438, 50, 'Umaria', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(788, 13, 'Uttarkashi', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(346, 18, 'Kodagu', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(762, 63, 'Rampur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(391, 3, 'Anuppur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(797, 8, 'Hooghly', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(574, 11, 'Kapurthala', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(378, 1, 'Agatti', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(367, 8, 'Kozhikode', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(743, 44, 'Kanpur Nagar', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(526, 11, 'Zunheboto', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(97, 10, 'Dhubri', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(793, 4, 'Burdwan (Bardhaman)', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(426, 38, 'Sagar', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(131, 10, 'East Champaran (Motihari)', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(503, 9, 'West Garo Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(268, 1, 'Bilaspur', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(369, 10, 'Palakkad', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(635, 10, 'Karur', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(431, 43, 'Shajapur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(513, 7, 'Saiha', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(66, 1, 'Tawang', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(689, 31, 'Yadadri Bhuvanagiri', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(201, 6, 'North West  Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(584, 21, 'Sangrur', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(736, 37, 'Hardoi', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(633, 8, 'Kanchipuram', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(397, 9, 'Bhopal', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(149, 28, 'Rohtas', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(606, 20, 'Jhunjhunu', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(334, 6, 'Bidar', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(516, 1, 'Dimapur', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(199, 4, 'North Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(422, 34, 'Raisen', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(135, 14, 'Jehanabad', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(676, 18, 'Nalgonda', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(582, 19, 'Rupnagar', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(332, 4, 'Bengaluru (Bangalore) Rural', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(694, 4, 'North Tripura', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(399, 11, 'Chhatarpur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(68, 3, 'East Kameng', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(608, 22, 'Karauli', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(221, 11, 'Dangs (Ahwa)', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(155, 34, 'Sitamarhi', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(338, 10, 'Chitradurga', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(692, 2, 'Gomati', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(95, 8, 'Darrang', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(265, 21, 'Sonipat', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(129, 8, 'Buxar', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(696, 6, 'South Tripura', 'TR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(215, 5, 'Banaskantha (Palanpur)', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(760, 61, 'Pratapgarh', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(357, 29, 'Vijayapura (Bijapur)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(671, 13, 'Mahabubnagar', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(491, 14, 'Tengnoupal', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(803, 14, 'Murshidabad', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(629, 4, 'Cuddalore', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(153, 32, 'Sheikhpura', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(587, 1, 'Ajmer', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(504, 10, 'West Jaintia Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(91, 4, 'Bongaigaon', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(151, 30, 'Samastipur', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(241, 31, 'Tapi (Vyara)', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(236, 26, 'Porbandar', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(669, 11, 'Komaram Bheem Asifabad', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(342, 14, 'Gadag', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(612, 26, 'Pratapgarh', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(72, 7, 'Lower Subansiri', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(715, 16, 'Basti', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(428, 40, 'Sehore', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(407, 19, 'Gwalior', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(507, 1, 'Aizawl', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(283, 3, 'Baramulla', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(238, 28, 'Sabarkantha (Himmatnagar)', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(93, 6, 'Charaideo', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(738, 39, 'Jalaun', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(360, 1, 'Alappuzha', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(497, 3, 'East Khasi Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(778, 3, 'Chamoli', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(718, 19, 'Budaun', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(631, 6, 'Dindigul', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(435, 47, 'Singrauli', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(413, 25, 'Katni', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(281, 1, 'Anantnag', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(590, 4, 'Baran', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(501, 7, 'South West Garo Hills ', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(330, 2, 'Ballari (Bellary)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(732, 33, 'Gonda', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(589, 3, 'Banswara', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(511, 5, 'Lunglei', 'MZ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(774, 75, 'Varanasi', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(70, 5, 'Kurung Kumey', 'AR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(243, 33, 'Valsad', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(610, 24, 'Nagaur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(340, 12, 'Davangere', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(278, 11, 'Solan', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(433, 45, 'Shivpuri', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(194, 2, 'Diu', 'DD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(543, 16, 'Kandhamal', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(498, 4, 'North Garo Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(409, 21, 'Hoshangabad', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(382, 5, 'Chethlath', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(309, 6, 'East Singhbhum', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(297, 17, 'Ramban', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(722, 23, 'Deoria', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(763, 64, 'Saharanpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(167, 5, 'Bemetara', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(380, 3, 'Androth', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(496, 2, 'East Jaintia Hills', 'ML');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(287, 7, 'Jammu', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(178, 16, 'Kondagaon', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(384, 7, 'Kadmath', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(707, 8, 'Azamgarh', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(449, 9, 'Dhule', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(171, 9, 'Dhamtari', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(111, 24, 'Morigaon', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(716, 17, 'Bhadohi', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(112, 25, 'Nagaon', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(249, 5, 'Fatehabad', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(765, 66, 'Sant Kabir Nagar', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(405, 17, 'Dindori', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(176, 14, 'Kabirdham (Kawardha)', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(654, 29, 'Tiruvarur', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(520, 5, 'Mokokchung', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(447, 7, 'Buldhana', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(354, 26, 'Tumakuru (Tumkur)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(173, 11, 'Gariyaband', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(109, 22, 'Lakhimpur', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(386, 9, 'Kilthan', 'LD');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(523, 8, 'Phek', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(786, 11, 'Tehri Garhwal', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(772, 73, 'Sultanpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(114, 27, 'Dima Hasao', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(713, 14, 'Barabanki', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(231, 21, 'Morbi', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(651, 26, 'Tiruppur', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(471, 31, 'Sindhudurg', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(251, 7, 'Hisar', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(50, 3, 'South Andaman', 'AN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(311, 8, 'Giridih', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(711, 12, 'Balrampur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(358, 30, 'Yadgir', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(191, 1, 'Dadra & Nagar Haveli', 'DH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(180, 18, 'Korea (Koriya)', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(647, 22, 'Theni', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(594, 8, 'Bikaner', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(709, 10, 'Bahraich', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(755, 56, 'Meerut', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(259, 15, 'Palwal', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(660, 2, 'Bhadradri Kothagudem', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(570, 7, 'Ferozepur', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(475, 35, 'Washim', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(451, 11, 'Gondia', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(105, 18, 'Kamrup', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(777, 2, 'Bageshwar', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(700, 1, 'Agra', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(307, 4, 'Dhanbad', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(157, 36, 'Supaul', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(592, 6, 'Bharatpur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(564, 1, 'Amritsar', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(429, 41, 'Seoni', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(116, 29, 'Sonitpur', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(525, 10, 'Wokha', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(295, 15, 'Pulwama', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(55, 4, 'Guntur', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(344, 16, 'Haveri', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(649, 24, 'Tiruchirappalli', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(169, 7, 'Bilaspur', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(400, 12, 'Chhindwara', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(305, 2, 'Chatra', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(301, 21, 'Srinagar', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(53, 2, 'Chittoor', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(118, 31, 'Tinsukia', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(796, 7, 'Darjeeling', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(636, 11, 'Krishnagiri', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(757, 58, 'Moradabad', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(566, 3, 'Bathinda', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(518, 3, 'Kohima', 'NL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(493, 16, 'Ukhrul', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(411, 23, 'Jabalpur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(299, 19, 'Samba', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(182, 20, 'Mungeli', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(784, 9, 'Pithoragarh', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(257, 13, 'Mahendragarh', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(356, 28, 'Uttara Kannada (Karwar)', 'KA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(568, 5, 'Fatehgarh Sahib', 'PB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(223, 13, 'Gandhinagar', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(720, 21, 'Chandauli', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(453, 13, 'Jalgaon', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(107, 20, 'Karimganj', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(561, 3, 'Pondicherry', 'PY');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(538, 11, 'Ganjam', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(394, 6, 'Barwani', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(159, 38, 'West Champaran', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(702, 3, 'Allahabad', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(782, 7, 'Nainital', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(668, 10, 'Khammam', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(404, 16, 'Dhar', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(602, 16, 'Jaipur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(99, 12, 'Goalpara', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(468, 28, 'Ratnagiri', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(247, 3, 'Charkhi Dadri', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(235, 25, 'Patan', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(125, 4, 'Banka', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(780, 5, 'Dehradun', 'UK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(315, 12, 'Jamtara', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(549, 22, 'Mayurbhanj', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(63, 12, 'West Godavari', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(454, 14, 'Jalna', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(662, 4, 'Jagtial', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(600, 14, 'Dungarpur', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(390, 2, 'Alirajpur', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(271, 4, 'Kangra', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(365, 6, 'Kollam', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(127, 6, 'Bhagalpur', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(539, 12, 'Jagatsinghapur', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(313, 10, 'Gumla', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(293, 13, 'Leh', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(806, 17, 'Paschim Medinipur (West Medinipur)', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(184, 22, 'Raigarh', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(456, 16, 'Latur', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(61, 10, 'Visakhapatnam', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(641, 16, 'Perambalur', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(482, 5, 'Imphal West', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(729, 30, 'Gautam Buddha Nagar', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(657, 32, 'Virudhunagar', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(596, 10, 'Chittorgarh', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(645, 20, 'Sivaganga', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(559, 1, 'Karaikal', 'PY');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(291, 11, 'Kulgam', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(769, 70, 'Siddharth Nagar', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(466, 26, 'Pune', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(638, 13, 'Nagapattinam', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(245, 1, 'Ambala', 'HR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(317, 14, 'Koderma', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(289, 9, 'Kathua', 'JK');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(370, 11, 'Pathanamthitta', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(209, 2, 'South Goa', 'GA');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(57, 6, 'Kurnool', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(551, 24, 'Nayagarh', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(460, 20, 'Nanded', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(123, 2, 'Arwal', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(363, 4, 'Kannur', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(188, 26, 'Surajpur  ', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(462, 22, 'Nashik', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(541, 14, 'Jharsuguda', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(402, 14, 'Datia', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(372, 13, 'Thrissur', 'KL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(204, 9, 'South East Delhi', 'DL');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(277, 10, 'Sirmaur (Sirmour)', 'HP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(808, 19, 'Purulia', 'WB');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(103, 16, 'Jorhat', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(478, 1, 'Bishnupur', 'MN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(229, 19, 'Mahisagar', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(165, 3, 'Balrampur', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(142, 21, 'Madhubani', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(706, 7, 'Auraiya', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(464, 24, 'Palghar', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(643, 18, 'Ramanathapuram', 'TN');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(101, 14, 'Hailakandi', 'AS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(547, 20, 'Koraput', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(767, 68, 'Shamali (Prabuddh Nagar)', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(598, 12, 'Dausa', 'RJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(684, 26, 'Suryapet', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(723, 24, 'Etah', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(664, 6, 'Jayashankar Bhoopalpally', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(704, 5, 'Amethi (Chatrapati Sahuji Mahraj Nagar)', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(545, 18, 'Kendujhar (Keonjhar)', 'OR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(396, 8, 'Bhind', 'MP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(319, 16, 'Lohardaga', 'JH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(163, 1, 'Balod', 'CH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(144, 23, 'Muzaffarpur', 'BR');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(682, 24, 'Sangareddy', 'TS');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(458, 18, 'Mumbai Suburban', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(473, 33, 'Thane', 'MH');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(233, 23, 'Navsari', 'GJ');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(59, 8, 'Prakasam', 'AP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(727, 28, 'Fatehpur', 'UP');
INSERT INTO public.districts
(id, district_id, district_name, state_code)
VALUES(120, 33, 'West Karbi Anglong', 'AS');

----------Master  client ----------
INSERT INTO public.client_details
(id, created_by, created_date, last_modified_date, modified_by, address, amount, description, email, is_active, is_es_slip_enabled, is_tax_included, mobile, "name", organization_name, plan_tenure, razor_pay_payment_id, plan_id, plan_activation_date, plan_expiry_date)
VALUES(51, NULL, '2022-07-14 16:34:53.645', '2022-07-14 16:34:53.645', NULL, 'Hyderabad', NULL, NULL, NULL, true, NULL, NULL, NULL, 'Otsi', 'Kalamadir Textiles', NULL, NULL, NULL, NULL, NULL);

----------Master Roles------------

INSERT INTO public."role"
(id, created_by, created_date, last_modified_date, modified_by, description, is_active, role_name, client_id, client_domian)
VALUES(57, NULL, '2022-07-14 17:11:42.359', '2022-07-14 17:11:42.359', NULL, 'he is a captain', false, 'captain', 51, NULL);
INSERT INTO public."role"
(id, created_by, created_date, last_modified_date, modified_by, description, is_active, role_name, client_id, client_domian)
VALUES(249, NULL, '2022-07-27 11:51:59.108', '2022-07-27 11:51:59.108', NULL, 'he is a client support person', false, 'client_support', 51, NULL);
INSERT INTO public."role"
(id, created_by, created_date, last_modified_date, modified_by, description, is_active, role_name, client_id, client_domian)
VALUES(266, NULL, '2022-07-27 19:22:17.276', '2022-07-27 19:22:17.276', NULL, 'he is a ticketing person', false, 'ticketing_person', 51, NULL);


-------------------Master User--------------

INSERT INTO public.user_details
(id, created_by, created_date, last_modified_date, modified_by, gender, is_active, is_customer, is_super_admin, phone_number, user_name, role_id)
VALUES(58, 0, '2022-07-14 17:14:57.881', '2022-07-14 17:14:57.881', NULL, NULL, true, false, false, '+918328155775', 'Captain', 57);

--------------- role_parentPrivileges-----------
INSERT INTO public.role_parent_privilages VALUES (249, 600);

INSERT INTO public.role_parent_privilages VALUES (266, 500);

INSERT INTO public.role_parent_privilages VALUES (57, 650);


--------------------role_subPrivilege-------------

INSERT INTO public.role_sub_privilages VALUES (249, 604);

INSERT INTO public.role_sub_privilages VALUES (249, 609);

INSERT INTO public.role_sub_privilages VALUES (249, 614);

INSERT INTO public.role_sub_privilages VALUES (57, 778);

INSERT INTO public.role_sub_privilages VALUES (57, 779);

INSERT INTO public.role_sub_privilages VALUES (57, 780);

INSERT INTO public.role_sub_privilages VALUES (266, 781);

INSERT INTO public.role_sub_privilages VALUES (266, 782);

INSERT INTO public.role_sub_privilages VALUES (266, 783);

------------------role_childPrivileges----------------

INSERT INTO public.role_child_privilages VALUES (249, 605);

INSERT INTO public.role_child_privilages VALUES (249, 606);

INSERT INTO public.role_child_privilages VALUES (249, 607);

INSERT INTO public.role_child_privilages VALUES (249, 608);

INSERT INTO public.role_child_privilages VALUES (249, 610);

INSERT INTO public.role_child_privilages VALUES (249, 611);

INSERT INTO public.role_child_privilages VALUES (249, 612);

INSERT INTO public.role_child_privilages VALUES (249, 613);

INSERT INTO public.role_child_privilages VALUES (249, 615);

INSERT INTO public.role_child_privilages VALUES (249, 616);

INSERT INTO public.role_child_privilages VALUES (249, 617);

INSERT INTO public.role_child_privilages VALUES (249, 618);








