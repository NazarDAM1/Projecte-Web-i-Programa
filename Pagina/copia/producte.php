<?php

include('database_connection.php');

// Depen de de la seva id que te la pagina fara la consulta amb la id i retornara els seus valors
$single = get_single_by_id($_GET['id']);
?>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/jquery-ui.css" rel="stylesheet">

    <title>Pagina producte</title>
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>

    <div class="pagina">
        <header>
            <div class="menu">
                <a class="pagina_principal" href="index.php"><img class="img_logo" src="img/img_logo/logo.png"></a>
                <div class="buscador_div">
                    <input class="buscador" type="text" id="input1" placeholder="Buscar...">
                </div>
                <div class="logo_red">
                    <a href="https://www.instagram.com/"><img src="img/img_logo/instagram.png" alt="logo instagram"></a>
                    <a href="https://es-es.facebook.com/"><img src="img/img_logo/facebook.png" alt="logo facebook"></a>
                    <a href="https://twitter.com/?lang=es"><img src="img/img_logo/twitter.png" alt="logo twitter"></a>
                </div>
            </div>
        </header>
        <main>
        <div class="productes_principal2">


            <!-- mostrar les dades de la consulta $single -->
            <div class="informacio_producte">
                <img class="imatge_producte" src="<?php echo $single["img"] ?>">
                <div class="informacio_producte2">
                    <p>Nom: <?php echo $single["nom_prod"] ?></p>
                    <p>Categoria: <?php echo $single["nom_cat"] ?></p>
                    <p>Material: <?php echo $single["nom_mat"] ?></p>
                    <p>Quantitat: <?php echo $single["quantitat"] ?></p>
                    <p>Preu: <?php echo $single["preu"] ?> $</p>
                    <p>Descripcio: <?php echo $single["descripcio"] ?></p>
                </div>
            </div>
            <h2 class="producte_semblant">Productes semblants</h2>

            <div class="productes_semblants">
                <?php

                // Depen de de la seva id que te la pagina fara la consulta de productes semblants amb la id i retornara els seus valors

                $singles2 = get_single_by_id2($_GET['id']);

                foreach ($singles2 as $single2) :  ?>

                    <!-- mostrar les dades de la consulta $single2 -->
                    <a href="producte.php?id=<?php echo $single2["id"] ?>">
                        <div class="informacio_producte_semblant">
                            <img class="imatge_producte_semblant" src="<?php echo $single2["img"] ?>">
                            <div class="informacio_producte2">
                                <p>Nom: <?php echo $single2["nom_prod"] ?></p>
                                <p>Categoria: <?php echo $single2["nom_cat"] ?></p>
                                <p>Material: <?php echo $single2["nom_mat"] ?></p>
                                <p>Quantitat: <?php echo $single2["quantitat"] ?></p>
                                <p>Preu: <?php echo $single2["preu"] ?> $</p>
                                <p>Descripcio: <?php echo $single2["descripcio"] ?></p>
                            </div>
                        </div>
                    </a>
                <?php endforeach; ?>

            </div>

        </div>
        </main>
        <footer>
            <div class="informacio_empresa">
                <p>BRICO DEPOT</p>
                <p>Numero de telefon 642424242</p>
                <p>Correu electronic: BricoDepo@gmail.com</p>
            </div>
            <div class="ubicacio">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d32790.85443838606!2d0.5873674736810613!3d41.60695486564505!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12a6e024dd1b9ef5%3A0x126af32d740e74a6!2sBrico%20Dep%C3%B4t%20Lleida!5e0!3m2!1ses!2ses!4v1638851669698!5m2!1ses!2ses" width="400" height="300" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
            </div>
            <div class="slideshow-container">

                <div class="mySlides_fade">

                    <img src="img/img_logo/brico.jpg">

                </div>

                <div class="mySlides_fade">

                    <img src="img/img_logo/brico2.jpg">

                </div>

                <div class="mySlides_fade">

                    <img src="img/img_logo/brico3.jpg">

                </div>

            </div>
            <br>

            <div class="dot">
                <span class="dot"></span>
                <span class="dot"></span>
                <span class="dot"></span>
            </div>



            <script>
                var slideIndex = 0;
                showSlides();

                function showSlides() {
                    var i;
                    var slides = document.getElementsByClassName("mySlides_fade");
                    var dots = document.getElementsByClassName("dot");
                    for (i = 0; i < slides.length; i++) {
                        slides[i].style.display = "none";
                    }
                    slideIndex++;
                    if (slideIndex > slides.length) {
                        slideIndex = 1
                    }
                    for (i = 0; i < dots.length; i++) {
                        dots[i].className = dots[i].className.replace(" active", "");
                    }
                    slides[slideIndex - 1].style.display = "block";
                    dots[slideIndex - 1].className += " active";
                    setTimeout(showSlides, 4000); // 
                }
            </script>
        </footer>
    </div>
</body>

</html>