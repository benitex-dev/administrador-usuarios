INSERT INTO permissions (permission_name) VALUES ('READ');
INSERT INTO permissions (permission_name) VALUES ('CREATE');
INSERT INTO permissions (permission_name) VALUES ('UPDATE');
INSERT INTO permissions (permission_name) VALUES ('DELETE');

INSERT INTO roles (role) VALUES ('ADMINISTRADOR');
INSERT INTO roles (role) VALUES ('ESTUDIANTE');
INSERT INTO roles (role) VALUES ('PROFESOR');

INSERT INTO roles_permissions (role_id, permission_id) SELECT r.id, p.id FROM roles r, permissions p WHERE r.role = 'ADMINISTRADOR';
INSERT INTO roles_permissions (role_id, permission_id) SELECT r.id, p.id FROM roles r, permissions p WHERE r.role = 'ESTUDIANTE' AND p.permission_name = 'READ';
INSERT INTO roles_permissions (role_id, permission_id) SELECT r.id, p.id FROM roles r, permissions p WHERE r.role = 'PROFESOR' AND p.permission_name IN ('READ', 'CREATE', 'UPDATE');
