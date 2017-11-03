(
        function (ng) {
            var mod = ng.module("marcoModule");
            mod.constant("marcosContext", "api/marcos");
            mod.controller('marcoUpdateCtrl', ['$scope', '$http', 'marcosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, marcosContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idMarco = $state.params.marcoId;

                    //Consulto el marco a editar.
                    $http.get(marcosContext + '/' + idMarco).then(function (response) {
                        var marco = response.data;
                        $scope.marcoName = marco.name;
                        $scope.marcoMaterial = marco.material;
                        $scope.marcoAlto = marco.alto;
                        $scope.marcoAncho = marco.ancho;
                        $scope.marcoImage = marco.image;
                        $scope.marcoValor = marco.valor;
      
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

                    $scope.createMarco = function () {
                        $http.put(marcosContext + "/" + idMarco, {
                            name: $scope.marcoName,
                            material: $scope.marcoMaterial,
                            alto: $scope.marcoAlto,
                            ancho: $scope.marcoAncho,
                            image: $scope.marcoImage,
                            valor: $scope.marcoValor
                        }).then(function (response) {
                            //Marco created successfully
                            $state.go('marcosList', {marcoId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);


