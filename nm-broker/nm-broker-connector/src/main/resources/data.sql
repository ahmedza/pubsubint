
insert into nm_subscription_type(id,subscription_type_name,destination_name,msg_time_to_live_mins) values('1', 'DEFAULT_SUBSCRIPTION', 'gcaa.nm.default.destination',2880)
insert into nm_subscription_type(id,subscription_type_name,destination_name,msg_time_to_live_mins) values('2', 'FlightPlanMessage', 'gcaa.nm.flightplans',360)
insert into nm_subscription_type(id,subscription_type_name,destination_name,msg_time_to_live_mins) values('3', 'FlightDataMessage', 'gcaa.nm.flightdata',5)