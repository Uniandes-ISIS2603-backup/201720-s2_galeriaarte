(
        function (ng) {
            var mod = ng.module("catalogoModule");
            mod.constant("catalogosContext", "api/catalogos");
            mod.controller('catalogoUpdateCtrl', ['$scope', '$http', 'catalogosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, catalogosContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var Catalogoid = $state.params.idCatalogo;

                    //Consulto el catálogo a editar
                    $http.get(catalogosContext + '/' + Catalogoid).then(function (response) {
                        var catalogo = response.data;
                        $scope.idCatalogo = catalogo.id;
                        $scope.categoriaCatalogo = catalogo.categoria;
                        $scope.imagenCatalogo = catalogo.imagen;
                        $scope.descripcionCatalogo = catalogo.descripcion;
                        
                        
                    });

                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                    };

                    $scope.createCatalogo = function () {
                        $http.put(catalogosContext + "/" + Catalogoid, {
                            id: $scope.idCatalogo,
                            categoria: $scope.categoriaCatalogo,
                            imagen: $scope.imagenCatalogo,
                            descripcion: $scope.descripcionCatalogo
                        }).then(function (response) {
                            //Catalogo created successfully
                            $state.go('catalogosList', {catalogoId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);




