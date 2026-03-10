rootProject.name = "toy-msa"

include(
    ":service-discovery",
    ":config-service",
    ":apigateway-service",
    ":modules:data-serializer",
    ":modules:event",
    ":supports:logging",
    ":supports:monitoring",
    ":user-service",
    ":catalog-service",
    ":order-service",
    ":first-service",
    ":second-service",
)