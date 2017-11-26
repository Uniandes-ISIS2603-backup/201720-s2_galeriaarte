(
        function (ng) {
            var mod = ng.module("clienteModule");
            mod.constant("clientesContext", "api/cliente");
            mod.constant("comentariosContext", "api/comentarios");
            mod.controller('clienteUpdateCtrl', ['$scope', '$http', 'clientesContext', '$state', 'comentariosContext', '$rootScope',
                function ($scope, $http, clientesContext, $state, comentariosContext, $rootScope) {
                    $rootScope.edit = true;

                    var idCliente = $state.params.clienteId;

                    var idsComentario = [];

                    $scope.allComentariosShow = [];

                    $http.get(clientesContext + '/' + idCliente).then(function (response) {
                        var cliente = response.data;
                        $scope.clienteName = cliente.name;
                        $scope.clienteTipoTarjeta = cliente.tipoTarjeta;
                        $scope.clienteNumeroTarjeta = cliente.numTarjeta;
                        $scope.allComentariosCliente = cliente.comentarios;
                        $scope.mergeComentarios($scope.allComentariosCliente);
                    });

                    /**
                     * 
                     * @param {type} comentarios
                     * @returns {undefined}
                     */
                    $scope.mergeComentarios = function (comentarios) {
                        for (var item in comentarios) {
                            idsComentario.push("" + comentarios[item].id);
                        }
                        $scope.getComentarios(comentarios);
                    };

                    /**
                     * 
                     * @param {type} comentarios
                     * @returns {undefined}
                     */
                    $scope.getComentarios = function (comentarios) {
                        $http.get(comentariosContext).then(function (response) {
                            $scope.Allcomentarios = response.data;
                            $scope.comentariosCliente = comentarios;

                            var filteredComentarios = $scope.Allcomentarios.filter(function (Allcomentarios) {
                                return $scope.comentariosCliente.filter(function (comentariosCliente) {
                                    return comentariosCliente.id === Allcomentarios.id;
                                }).length === 0;
                            });

                            $scope.allComentariosShow = filteredComentarios;

                        });
                    };
                                      
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
                        idsComentario.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        var index = idsComentario.indexOf(data);
                        if (index > -1) {
                            idsComentario.splice(index, 1);
                        }
                    };

                    $scope.createCliente = function () {
                        $scope.newComentarios();
                        $http.put(clientesContext + "/" + idCliente, {
                            name: $scope.clienteName,
                            tipoTarjeta: $scope.clienteTipoTarjeta,
                            numTarjeta: $scope.clienteNumeroTarjeta
                        }).then(function (response) {
                            $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newComentarios = function () {
                        $scope.allComentariosCliente = [];
                        for (var ite in idsComentario) {
                            for (var all in $scope.Allcomentarios) {
                                if ($scope.Allcomentarios[all].id === parseInt(idsComentario[ite])) {
                                    $scope.allComentariosCliente.push($scope.Allcomentarios[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);