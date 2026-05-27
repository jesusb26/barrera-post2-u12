package com.universidad.sistemapedidos;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import com.universidad.sistemapedidos.pedidos.domain.ProcesadorPedido;

@AnalyzeClasses(packages = "com.universidad.sistemapedidos",
        importOptions = ImportOption.DoNotIncludeTests.class)
public class ReglasArquitectura {

    // Regla 1: El dominio no depende de infraestructura ni adaptadores
    @ArchTest
    static final ArchRule dominioAislado
            = noClasses().that().resideInAPackage("..pedidos.domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage(
                            "..pedidos.infrastructure..",
                            "..pedidos.controllers..",
                            "javax.persistence..",
                            "org.springframework.mail..",
                            "org.springframework.data.."
                    );

    // Regla 2: Los controladores solo acceden a la Fachada, a su propio paquete (DTOs), al dominio, a Spring y Java
    @ArchTest
    static final ArchRule controladorSoloFacade
            = classes().that().resideInAPackage("..pedidos.controllers..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "..pedidos.application..", // permite toda la capa de aplicación
                            "..pedidos.domain..",
                            "..pedidos.controllers..",
                            "org.springframework..",
                            "java..",
                            "jakarta.."
                    );

    // Regla 3: Los puertos de dominio son interfaces (ejemplo: ProcesadorPedido)
    // Aplicamos a todas las clases dentro del subpaquete ..domain.. que tengan nombre "ProcesadorPedido"
    // o podríamos buscar clases que sean implementadas por otro (más genérico)
    @ArchTest
    static final ArchRule puertosComoInterfaces
            = classes().that().resideInAPackage("..pedidos.domain..")
                    .and().haveSimpleName("ProcesadorPedido")
                    .should().beInterfaces();

    // Regla 4: Los procesadores (adaptadores concretos) implementan el puerto ProcesadorPedido
    @ArchTest
    static final ArchRule procesadoresImplementanPuerto
            = classes().that().resideInAPackage("..pedidos.application.processors..")
                    .should().implement(ProcesadorPedido.class);

    // Regla 5: La infraestructura no accede a los adaptadores REST (controladores)
    @ArchTest
    static final ArchRule infraNoAccedeRest
            = noClasses().that().resideInAPackage("..pedidos.infrastructure..")
                    .should().accessClassesThat()
                    .resideInAPackage("..pedidos.controllers..");
}
