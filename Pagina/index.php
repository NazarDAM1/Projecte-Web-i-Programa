<?php

//index.php

include('database_connection.php');

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

        <div class="productes_principal">



            <section class="filtrar">
                <h2>Seleccio de productes</h2><br>


                <div class="dropdown">
                    <button class="dropbtn">Ordenar</button>

                    <div class="dropdown-content">
                        
                        <label >
                            <input type="checkbox" class="radio common_selector nom_prod" value="1" name="fooby[1][]"/>Nom asc
                        </label>
                            
                            <br>
                        <label >
                            <input type="checkbox" class="radio common_selector preu" value="1" name="fooby[1][]"/>Preu desc
                        </label>
                            
                    </div>
                </div>


                <div class="list-group">
                    <h3>Preu</h3>
                    <input type="hidden" id="hidden_minimum_price" value="1" />
                    <input type="hidden" id="hidden_maximum_price" value="300" />
                    <p id="price_show">1 $ - 300 $</p>
                    <div id="price_range"></div>
                </div>

                <div class="categories">
                    <a class="a_filtrar"> Categories</a>


                    <?php

                    $query = "select nom_cat from categoria order by nom_cat";
                    $statement = $connect->prepare($query);
                    $statement->execute();
                    $result = $statement->fetchAll();
                    foreach ($result as $row) {
                    ?>

                        <label class="container">

                            <input type="checkbox" class="common_selector nom_cat" value="<?php echo $row['nom_cat']; ?>"> <?php echo $row['nom_cat']; ?>
                            <span class="checkmark"></span>
                        </label>


                    <?php
                    }

                    ?>

                </div>

                <div class="Material">
                    <a class="a_filtrar"> Material</a>

                    <?php

                    $query = "select nom_mat from material order by nom_mat";
                    $statement = $connect->prepare($query);
                    $statement->execute();
                    $result = $statement->fetchAll();
                    foreach ($result as $row) {
                    ?>

                        <label class="container">
                            <input type="checkbox" class="common_selector nom_mat" value="<?php echo $row['nom_mat']; ?>"> <?php echo $row['nom_mat']; ?>
                            <span class="checkmark"></span>
                        </label>

                    <?php
                    }

                    ?>

                </div>



            </section>
            
            <div class="producte2">

            </div>

        </div>

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



            <script src="js/buscador.js"></script>


            <script>
                $("input:checkbox").on('click', function() {
                    // in the handler, 'this' refers to the box clicked on
                    var $box = $(this);
                    if ($box.is(":checked")) {
                        // the name of the box is retrieved using the .attr() method
                        // as it is assumed and expected to be immutable
                        var group = "input:checkbox[name='" + $box.attr("name") + "']";
                        // the checked state of the group/box on the other hand will change
                        // and the current value is retrieved using .prop() method
                        $(group).prop("checked", false);
                        $box.prop("checked", true);
                    } else {
                        $box.prop("checked", false);
                    }
                });
            </script>



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


        <!-- Page Content -->

        <style>
            #loading {
               
                background: url('loader.gif') no-repeat center;
                height: 300px;
                min-width:300px;
                
            }
        </style>

        <script>
            $(document).ready(function() {

                producte2();

                function producte2() {
                    $('.producte2').html('<div id="loading" style="" ></div>');
                    var action = 'fetch_data';
                    var minimum_price = $('#hidden_minimum_price').val();
                    var maximum_price = $('#hidden_maximum_price').val();
                    var nom_cat = get_filter('nom_cat');
                    var nom_mat = get_filter('nom_mat');
                    var nom_prod = get_filter('nom_prod');
                    var preu = get_filter('preu');
                    $.ajax({
                        url: "fetch_data.php",
                        method: "POST",
                        data: {
                            action: action,
                            minimum_price: minimum_price,
                            maximum_price: maximum_price,
                            nom_cat: nom_cat,
                            nom_mat: nom_mat,
                            nom_prod:nom_prod,
                            preu:preu,



                        },
                        success: function(data) {
                            $('.producte2').html(data);
                        }
                    });
                }

                function get_filter(class_name) {
                    var filter = [];
                    $('.' + class_name + ':checked').each(function() {
                        filter.push($(this).val());
                    });
                    return filter;
                }

                $('.common_selector').click(function() {
                    producte2();
                });

                $('#price_range').slider({
                    range: true,
                    min: 1,
                    max: 300,
                    values: [1 , 300],
                    step: 1,
                    stop: function(event, ui) {
                        $('#price_show').html(ui.values[0]+' $' + ' - ' +ui.values[1]+' $');
                        $('#hidden_minimum_price').val(ui.values[0]);
                        $('#hidden_maximum_price').val(ui.values[1]);
                        producte2();
                    }
                });

            });
        </script>

    </div>

</body>

</html>