(
        function (ng) {
            var mod = ng.module("catalogoModule");
            mod.constant("catalogosContext", "api/catalogos");
            mod.controller('catalogoUpdateCtrl', ['$scope', '$http', 'catalogosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, catalogosContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idCatalogo = $state.params.catalogoId;

                    //Consulto el catálogo a editar
                    $http.get(catalogosContext + '/' + idCatalogo).then(function (response) {
                        var catalogo = response.data;
                        $scope.categoriaCatalogo = catalogo.categoria;
                        
                        
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
                        $http.put(catalogosContext + "/" + idCatalogo, {
                            categoria: $scope.categoriaCatalogo,
                        }).then(function (response) {
                            //Blog created successfully
                            $state.go('catalogosList', {catalogoId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);




