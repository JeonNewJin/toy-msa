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
	$(foreach svc,$(SERVICES),docker build -t $(svc):latest apps/$(svc);)

docker-build-%: build-%
	docker build -t $*:latest apps/$*

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

# ── All-in-one ─────────────────────────────────────────

deploy: docker-build k8s-deploy

.PHONY: init build docker-build k8s-deploy k8s-delete deploy
