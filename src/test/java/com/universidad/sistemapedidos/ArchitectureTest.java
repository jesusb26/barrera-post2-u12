package com.universidad.sistemapedidos;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.universidad.sistemapedidos", 
                importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {

    // Regla para controladores: solo dependen de application, domain, Spring, etc.
    @ArchTest
    static final ArchRule controller_only_depends_on_facade =
            classes().that().resideInAPackage("..controllers..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "..controllers..",
                            "..application..",   // donde está FachadaPedidos
                            "..domain..",        // Pedido, enums
                            "java..",
                            "javax..",
                            "jakarta..",
                            "org.springframework.."
                    );

    // Regla para la fachada: no debe depender de implementaciones concretas de listeners
    // (asumiendo que los listeners están en un paquete ..listeners..)
    @ArchTest
    static final ArchRule facade_not_depend_on_concrete_notifications =
            noClasses().that().resideInAPackage("..application..")
                    .and().haveSimpleName("FachadaPedidos")
                    .should().dependOnClassesThat().resideInAnyPackage("..listeners..");

}