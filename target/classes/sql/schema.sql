CREATE DATABASE wildlife_tourism;

USE wildlife_tourism;

CREATE TABLE admin_panel(
                            password VARCHAR(15)  ,
                            admin_email VARCHAR(120) PRIMARY KEY,
                            username VARCHAR(50)
);

CREATE TABLE cashier(
                        cashier_id VARCHAR(10) PRIMARY KEY ,
                        username VARCHAR(50)  ,
                        password VARCHAR(15)  ,
                        admin_email VARCHAR(120) ,
                        FOREIGN KEY(admin_email) REFERENCES admin_panel(admin_email) on update cascade on delete cascade
);


CREATE TABLE package(
                        package_id VARCHAR(10) PRIMARY KEY,
                        description TEXT,
                        package_price DECIMAL(10,2),
                        package_features TEXT,
                        package_type TEXT
);

CREATE TABLE tickets(
                        ticket_id VARCHAR(10) PRIMARY KEY,
                        price DECIMAL(10,2)  ,
                        ticket_type VARCHAR(50),
                        cashier_id VARCHAR(10),
                        ticket_count INT,
                        package_id VARCHAR(10) ,
                        FOREIGN KEY(cashier_id) REFERENCES cashier(cashier_id) on update cascade on delete cascade,
                        FOREIGN KEY(package_id) REFERENCES package(package_id) on update cascade on delete cascade
);



CREATE TABLE tourist(
                        tourist_id VARCHAR(10) PRIMARY KEY,
                        nic VARCHAR(20) unique  ,
                        name TEXT not null ,
                        adult_or_child VARCHAR(10),
                        tourist_email VARCHAR(120)  ,
                        phone_number VARCHAR(20)  ,
                        local_or_not VARCHAR(50),
                        cashier_id VARCHAR(10)  ,
                        address TEXT,
                        FOREIGN KEY(cashier_id) REFERENCES cashier(cashier_id) on update cascade on delete cascade
);




CREATE TABLE finance_details(
                                tourist_id VARCHAR(10),
                                ticket_id VARCHAR(10),
                                package_id VARCHAR(10),
                                ticket_price DECIMAL(10,2),
                                package_price DECIMAL(10,2),
                                paid_amount DECIMAL(10,2),
                                qty INT,
                                FOREIGN KEY(ticket_id) REFERENCES tickets(ticket_id) on update cascade on delete cascade,
                                FOREIGN KEY(tourist_id) REFERENCES tourist(tourist_id) on update cascade on delete cascade
);


CREATE TABLE tourist_guide(
                              guide_nic  VARCHAR(20) PRIMARY KEY,
                              name TEXT,
                              phone_number VARCHAR(20),
                              licence_expair_date DATE,
                              address TEXT,
                              package_id VARCHAR(10),
                              admin_email VARCHAR(120)  ,
                              FOREIGN KEY(package_id) REFERENCES package(package_id) on update cascade on delete cascade,
                              FOREIGN KEY(admin_email) REFERENCES admin_panel(admin_email) on update cascade on delete cascade
);


CREATE TABLE jeep_driver(
                            driver_id VARCHAR(10) PRIMARY KEY ,
                            driver_nic VARCHAR(20)  ,
                            name TEXT NOT NULL ,
                            package_id VARCHAR(10) ,
                            phone_number VARCHAR(20),
                            admin_email VARCHAR(120)  ,
                            licence_expair_date DATE,
                            FOREIGN KEY(package_id) REFERENCES package(package_id) on update cascade on delete cascade,
                            FOREIGN KEY(admin_email) REFERENCES admin_panel(admin_email) on update cascade on delete cascade
);



CREATE TABLE vehicle(
                        registration_id VARCHAR(10) PRIMARY KEY,
                        package_id VARCHAR(10),
                        admin_email VARCHAR(120) ,
                        permit_no INT,
                        permit_expair_date DATE,
                        licence_expair_date DATE,
                        FOREIGN KEY(package_id) REFERENCES package(package_id) on update cascade on delete cascade,
                        FOREIGN KEY(admin_email) REFERENCES admin_panel(admin_email) on update cascade on delete cascade
);



