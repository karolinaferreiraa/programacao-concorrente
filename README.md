Responda as seguintes perguntas:

Qual foi o speedup1 obtido?
R: Rodada 1
10 threads: 299 / 126 ≈ 2,37
100 threads: 299 / 105 ≈ 2,84
Rodada 2
10 threads: 286 / 103 ≈ 2,77
100 threads: 286 / 236 ≈ 1,21

Teve algum caso que o speedup foi negativo?
R: Não. Em todos os testes o tempo com threads foi menor que o sequencial.

Repita os testes acima usando Threads virtuais.
Houve diferenças entre Threads de plataforma e Threads virtuais? Se houve, quais foram e explique o porquê das diferenças.
R: Sim, mas nesse caso a diferença é pequena porque a tarefa depende mais da CPU do que de operações de espera.
