Create `config.properties` in same jar file location
Add:
LDAP.HOST=192.168.xxx.xxx
LDAP.PORT=389
LDAP.BASE.DN=CN=Users,DC=domain,DC=com
BIND.USR=CN=Administrator,CN=Users,DC=domain,DC=com
BIND.USER.PASSWORD=password
SearchFilter=(&(objectClass=person)(memberOf=CN=group,CN=Users,DC=domain,DC=com))
Output=results.txt
