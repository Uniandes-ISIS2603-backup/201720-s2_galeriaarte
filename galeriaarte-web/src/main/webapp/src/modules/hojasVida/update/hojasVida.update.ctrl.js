(
        function (ng) {
            var mod = ng.module("hojaVidaModule");
            mod.constant("hojasVidaContext", "api/hojasVida");
            mod.controller('hojaVidaUpdateCtrl', ['$scope', '$http', 'hojasVidaContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, hojasVidaContext, $state, $rootScope) {
                    $rootScope.edit = true;

                    var idHojaVida = $state.params.hojaVidaId;

                    //Consulto la hoja a editar.
                    $http.get(hojasVidaContext + '/' + idHojaVida).then(function (response) {
                        var hojaVida = response.data;
                        $scope.hojaVidaId = hojaVida.id;
                        $scope.hojaVidaTrayectoria = hojaVida.trayectoria;
                        $scope.hojaVidaAlmaMater = hojaVida.almaMater;
                        $scope.hojaVidaNacionalidad = hojaVida.nacionalidad;
                        $scope.hojaVidaArtista = hojaVida.artista;
                       
                        
      
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

                    $scope.createHojaVida = function () {
                        $http.put(hojasVidaContext + "/" + idHojaVida, {
                            id: $scope.hojaVidaId,
                            trayectoria: $scope.hojaVidaTrayectoria,
                            almaMater: $scope.hojaVidaAlmaMater,
                           nacionalidad: $scope.hojaVidaNacionalidad,
                           artista: $scope.hojaVidaArtista
                           
                        }).then(function (response) {
                            //Obra created successfully
                            $state.go('hojasVidaList', {hojaVidaId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);


