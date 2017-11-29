(
        function (ng) {
            var mod = ng.module("artistaModule");
            mod.constant("artistasContext", "api/artistas");
            mod.controller('artistaUpdateCtrl', ['$scope', '$http', 'artistasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, artistasContext, $state, $rootScope) {
                    $rootScope.edit = true;

                    var idArtista = $state.params.artistaId;

                    //Consulto el artista a editar.
                    $http.get(artistasContext + '/' + idArtista).then(function (response) {
                        var artista = response.data;
                        $scope.artistaId = artista.id;
                        $scope.artistaName = artista.name;
                        $scope.artistaImagen = artista.imagen;
                        $scope.artistaHojaVida = artista.hoja;
                        
      
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

                    $scope.createArtista = function () {
                        $http.put(artistasContext + "/" + idArtista, {
                            id: $scope.artistaId,
                            name: $scope.artistaName,
                           imagen: $scope.artistaImagen,
                           hoja: $scope.artistaHojaVida
                        }).then(function (response) {
                            //Marco created successfully
                            $state.go('artistasList', {artistaId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);


