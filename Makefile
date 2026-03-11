SERVICES = apigateway-service user-service order-service catalog-service

# ── Git ────────────────────────────────────────────────

init:
	git config core.hooksPath .githooks
	chmod +x .githooks/pre-commit

# ── Build ──────────────────────────────────────────────

build:
	./gradlew $(addprefix :apps:,$(addsuffix :bootJar,$(SERVICES)))

build-%:
	./gradlew :apps:$*:bootJar

# ── Docker ─────────────────────────────────────────────

docker-build: build
	$(foreach svc,$(SERVICES),docker build -t $(svc):latest -f apps/$(svc)/Dockerfile .;)

docker-build-%: build-%
	docker build -t $*:latest -f apps/$*/Dockerfile .

# ── Monitoring (local) ──────────────────────────────────

monitoring-up:
	docker compose -f docker/monitoring-compose.yml up -d

monitoring-down:
	docker compose -f docker/monitoring-compose.yml down

monitoring-logs:
	docker compose -f docker/monitoring-compose.yml logs -f

# ── K8s Deploy ─────────────────────────────────────────

k8s-deploy:
	kubectl apply -f k8s/namespace.yml
	kubectl apply -f k8s/secrets.yml
	kubectl apply -f k8s/rbac.yml
	kubectl apply -f k8s/infra/
	kubectl apply -f k8s/monitoring/
	kubectl apply -f k8s/apps/

k8s-delete:
	kubectl delete -f k8s/apps/
	kubectl delete -f k8s/monitoring/
	kubectl delete -f k8s/infra/
	kubectl delete -f k8s/rbac.yml
	kubectl delete -f k8s/secrets.yml
	kubectl delete -f k8s/namespace.yml

k8s-restart-%:
	kubectl rollout restart deployment/$* -n toy-msa

k8s-deploy-%: docker-build-%
	kubectl apply -f k8s/apps/$*.yml
	kubectl rollout restart deployment/$* -n toy-msa

# ── All-in-one ─────────────────────────────────────────

deploy: docker-build k8s-deploy

.PHONY: init \
        build docker-build \
        monitoring-up monitoring-down monitoring-logs \
        k8s-deploy k8s-delete deploy
