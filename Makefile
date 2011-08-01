#
# Makefile for the CDF project
#
#
# @author: Jean-Lou Dupont
#

REPO=git@github.com:jldupont/cdf.git


all:
	@echo "Options (targets):"
	@echo " install"
	@echo " install_local"
	@echo " update"
	@echo " help"
	
help:
	@echo " * GIT configuration must be done"
	
install_local: common
	@echo "** Local installation"
	
	@install ${CURDIR}/src/config/supervisord_local.conf     /etc/supervisord.conf
	@install ${CURDIR}/src/config/redis.conf                 /etc/redis.conf


## Install symlinks to commands
##
install: common
	@echo "** EC2 installation"
	@install ${CURDIR}/src/config/supervisord.conf     /etc/supervisord.conf
	@install ${CURDIR}/src/config/redis.conf           /etc/redis.conf

common:
	@install -d /usr/bin/cdf
	@install ${CURDIR}/src/commands/fetcher_agent   /usr/bin/cdf/fetcher_agent
	@install ${CURDIR}/src/commands/gcrawler_agent  /usr/bin/cdf/gcrawler_agent
	@install ${CURDIR}/src/commands/leader_agent    /usr/bin/cdf/leader_agent
	@install ${CURDIR}/src/commands/gc_agent        /usr/bin/cdf/gc_agent
	@install ${CURDIR}/src/commands/gcache_agent    /usr/bin/cdf/gcache_agent
	@install ${CURDIR}/src/commands/glinker_agent   /usr/bin/cdf/glinker_agent
	@install ${CURDIR}/src/commands/nodehound_agent /usr/bin/cdf/nodehound_agent

	
	@install -d /etc/cdf
	@install ${CURDIR}/src/config/default_config.yaml /etc/cdf/default_config.yaml

	@install -d /var/lib/cloud/data/scripts
	@install ${CURDIR}/src/config/instance_id  /var/lib/cloud/data/scripts/10instance_id
	@install ${CURDIR}/src/config/redis        /var/lib/cloud/data/scripts/15redis
	@install ${CURDIR}/src/config/redis_db     /var/lib/cloud/data/scripts/16redis_db
	@install ${CURDIR}/src/config/supervisord  /var/lib/cloud/data/scripts/50supervisord
		
	
update:
	
	
	
.phony: install, update, help	