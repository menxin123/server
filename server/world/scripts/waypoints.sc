__config() -> {

  'stay_loaded'-> true,
  'scope' -> 'global',

  'commands' -> {
    'add <name>' -> ['add',null,null],
    'add <name> <pos>' -> ['add',null],
    'add <name> <pos> <commit>' -> 'add',
    'del <waypointname>' -> 'del',
    'list' -> 'list',
    'dashboard' -> 'dashboard',
    'tp <waypointname>' -> 'tp',
  },

  'arguments' -> {

    'name' -> {
      'type' -> 'string',
      'suggest' -> [],
    },

    'waypointname' -> {
      'type' -> 'string',
      'suggester' -> _(args) -> global_waypoints_name(),
    },

    'commit' -> {
      'type' -> 'text',
      'suggest' -> [],
    },
  },
};

//INIT

global_waypoints = read_file('waypoints','JSON');

if(
  global_waypoints == null,
  global_waypoints = {}
);

//UTILS

global_waypoints_name() -> (
  res = [];
  for(global_waypoints,res+=('"'+_+'"'));
  res
);

_error(msg) ->(
  print(player(),format(str('r %s',msg)));
  exit()
);


savePoints() -> (
  write_file('waypoints','JSON',global_waypoints);
);

waypointinfo(waypointname) -> (
  res = [];

  res += ' [ ';
  res += str('!/waypoints del "%s"',waypointname);
  res += str('^g /waypoints del "%s"',waypointname);
  res += ' ] ';

  res += ' [ ';
  res += str('!/waypoints tp "%s"',waypointname);
  res += str('^g /waypoints tp "%s"',waypointname);
  res += ' ] ';

  res += str('blu %d %d %d',global_waypoints:waypointname:'pos');
  res += '  ';

  res += str('by %s',waypointname);
  res += str('^g %s',global_waypoints:waypointname:'commit');

  res;
  );


// MINECRAFT COMMANDS

add(name, poi_pos, commit) -> (

  if(

    //if
    has(global_waypoints, name),
    _error('You are trying to overwrite an existing waypoint.Please delete it first.'),

    //else
    player = player();
    if (poi_pos == null, poi_pos=player~'pos');

    global_waypoints:name = {
      'pos' -> poi_pos,
      'commit' -> commit,
      'author' -> player,
      'dimension' -> player~'dimension',
    };

    text = format(
      str('g Added new waypoint '),
      str('by %s', name + ' '),
      str('g at %s %s %s', map(poi_pos, round(_))),
    );

    print(player, text);
    savePoints();
  );
);

del(waypointname) -> (
  if(

    //if
    delete(global_waypoints,waypointname),
    print(player(),'Waypoint '+waypointname+' deleted.'),

    //else
    _error('Waypoint '+waypointname+' does not exist'),
  );
  savePoints();
);


list() -> (
  if(
    //if
    length(global_waypoints) == 0,
    _error('There are no wayopint!'),
    //else
    print(player(),format('bc === List of current waypoints ==='));
    for(global_waypoints,print(format(waypointinfo(_))));
  );
);

dashboard() -> (
  print(player(),format('bc === DASH BOARD ==='));
);

tp(waypointname) ->(
  player = player();
  command = str('/execute in %s run tp @e[type=player,gamemode=!survival,gamemode=!adventure,name=%s] %s %s %s',
    global_waypoints:waypointname:'dimension',
    player,
    global_waypoints:waypointname:'pos':0,
    global_waypoints:waypointname:'pos':1,
    global_waypoints:waypointname:'pos':2,
  );
  if(
    run(command):0!=1, 
    ,
    _error('Your gamemode is survival or adventure, or waypoint does not exist');
  );
);
