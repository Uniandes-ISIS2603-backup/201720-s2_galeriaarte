(
        function (ng) {
            var mod = ng.module("obraModule");
            mod.constant("obrasContext", "api/obras");
            mod.controller('obraUpdateCtrl', ['$scope', '$http', 'obrasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, obrasContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idObra = $state.params.obraId;

                    //Consulto la obra a editar.
                    $http.get(obrasContext + '/' + idObra).then(function (response) {
                        var obra = response.data;
                        $scope.obraId = obra.id;
                        $scope.obraName = obra.name;
                        $scope.obraTipo = obra.tipo;
                        $scope.obraCantidad = obra.cantidad;
                        $scope.obraValor = obra.valor;
                        $scope.obraImagen = obra.imagen;
                        
      
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

                    $scope.createObra = function () {
                        $http.put(obrasContext + "/" + idObra, {
                            id: $scope.obraId,
                            name: $scope.obraName,
                            tipo: $scope.obraTipo,
                           cantidad: $scope.obraCantidad,
                           valor: $scope.obraValor,
                           imagen: $scope.obraImagen
                        }).then(function (response) {
                            //Obra created successfully
                            $state.go('obrasList', {obraId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);


