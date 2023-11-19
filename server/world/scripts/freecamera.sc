__config()-> {
  'stay_loaded' -> true,

  'commands' -> {
    '' -> 'freecamera',
  },
};

//INIT
global_playerdata = read_file('freecameraData','JSON');

if(
  global_playerdata == null,
  global_playerdata = {};
);

//UTILS
_error(msg) ->(
  print(player(),format(str('r %s',msg)));
  exit()
);

savedata(player) -> (
  name = str(player);
  global_playerdata:name = {
    'gamemode' -> player~'gamemode',
    'dimension' -> player~'dimension',
    'pos' -> player~'pos',
    'yaw' -> player~'yaw',
    'pitch' -> player~'pitch',
  };
  write_file('freecameraData','JSON',global_playerdata);
);

//COMMANDS
freecamera() -> (
  player = player();
  playername = str(player);
  if(
    //if
    player~'gamemode' == 'spectator',
    if(length(global_playerdata) == 0,_error('error:no data?!'));
    run(str('/execute in %s run tp %s %s %s %s %s %s',
      global_playerdata:playername:'dimension',
      player,
      global_playerdata:playername:'pos':0,
      global_playerdata:playername:'pos':1,
      global_playerdata:playername:'pos':2,
      global_playerdata:playername:'yaw',
      global_playerdata:playername:'pitch',
    ));
    run(str('/execute in %s run gamemode %s %s',
      global_playerdata:playername:'dimension',
      global_playerdata:playername:'gamemode',
      player,
    ));
    print(player,format(str('bc You are change gamemode to %s!',global_playerdata:playername:'gamemode'))),
    //else
    savedata(player);
    run(str('/execute in %s run gamemode spectator %s',
      player~'dimension',
      player,
    ));
    print(player,format('bc You are change gamemode to spectator!'));
  );
);
